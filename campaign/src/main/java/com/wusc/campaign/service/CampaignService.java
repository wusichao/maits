package com.wusc.campaign.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wusc.auth.utils.ResultUtil;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.dao.CampaignMapper;
import com.wusc.campaign.dto.RedisDTO;
import com.wusc.campaign.model.Campaign;
import com.wusc.campaign.utils.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wusc on 2017/12/21
 */

@Service
public class CampaignService {
    private static final Logger log= LoggerFactory.getLogger(ChannelService.class);
    @Autowired
    private CampaignMapper campaignMapper;
    @Autowired
    private CreativeService creativeService;
    @Autowired
    private ChannelService channelService;

    @Transactional
    @CacheEvict(value = "campaign", allEntries = true)
    public ReturnResult add(Campaign campaign){
        try{
            campaignMapper.insert(campaign);
        }catch (Exception e){
           log.error("add error",e);
           return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "campaign", allEntries = true)
    public ReturnResult delete(Long id){
        try{
            creativeService.deleteByCampaignId(id);
            campaignMapper.deleteById(id);
        }catch(Exception e){
            log.error("delete error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "campaign", allEntries = true)
    public ReturnResult update(Campaign campaign){
        try{
            campaignMapper.updateById(campaign);
        }catch (Exception e){
            log.error("update error",e);
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Cacheable(value ="campaign" ,keyGenerator ="wrapperKey" )
    public ReturnResult select(Wrapper wrapper,Integer limit,Integer offset,String sort,String order){
        Map<String,Object> mapData = new HashMap<>();
        Page<Campaign> page = new PageFactory<Campaign>().defaultPage();
        try {
            page.setRecords(campaignMapper.selectPage(page,wrapper));
        }catch (Exception e){
            log.error("select error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        mapData.put("size", page.getTotal());
        mapData.put("data", page.getRecords());
        return new ReturnResult(ResultUtil.SUCCESS_CODE,mapData);
    }
}
