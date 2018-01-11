package com.wusc.campaign.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import com.wusc.campaign.dao.CampaignMapper;
import com.wusc.campaign.model.Campaign;
import com.wusc.campaign.model.Channel;
import com.wusc.campaign.model.Creative;
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
    public ReturnResult select(Wrapper wrapper, Integer limit, Integer offset, String sort, String order){
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

    @Cacheable(value = "maitsCode",key ="#id")
    public ReturnResult getMaitsCode(Long id) {
        Map<String,Object> mapData = new HashMap<>();
        Campaign c =null;
        List<Channel> channels =null;
        List<Creative> createves =null;
        try{
             c=campaignMapper.selectById(id);
             if(c==null){
                return new ReturnResult(ResultUtil.SUCCESS_CODE,"invalid campaign");
             }
            channels=channelService.getChannelByIds(c.getChannelIds());
            createves=creativeService.getCreateveByIds(c.getCreativeIds());
        }catch (Exception e){
            log.error("getMaitsCode error");
        }
        StringBuffer clickStrId=new StringBuffer("c?");
        StringBuffer ImpStrId=new StringBuffer("i?");
        StringBuffer strName=new StringBuffer();
        ImpStrId.append("a="+c.getAccountId());
        ImpStrId.append("&c="+id);
        clickStrId.append("a="+c.getAccountId());
        clickStrId.append("&c="+id);
        strName.append(c.getName()+" ");
        for(Channel channel :channels){
            for(Creative creative:createves){
                clickStrId.append("&h="+channel.getId());
                clickStrId.append("&e="+creative.getId());
                ImpStrId.append("&e="+creative.getId());
                ImpStrId.append("&h="+channel.getId());
                strName.append(channel.getName()+"");
                strName.append(creative.getName());
                mapData.put(strName.toString(),ImpStrId);
                mapData.put(strName.toString(),clickStrId);
            }
        }
        return new ReturnResult(ResultUtil.SUCCESS_CODE,mapData);
    }
}
