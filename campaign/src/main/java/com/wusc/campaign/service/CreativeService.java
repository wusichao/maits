package com.wusc.campaign.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wusc.auth.utils.ResultUtil;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.dao.CreativeMapper;
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
import java.util.Map;

/**
 * create by wusc on 2017/12/21
 */
@Service
public class CreativeService {
    private static final Logger log = LoggerFactory.getLogger(CreativeService.class);
    @Autowired
    private CreativeMapper creativeMapper;

    @Transactional
    @CacheEvict(value = "creative", allEntries = true)
    public ReturnResult add(Creative creative){
        try{
            creativeMapper.insert(creative);
        }catch(Exception e){
            log.error("add error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "creative", allEntries = true)
    public ReturnResult delete(Long id){
        try{
            creativeMapper.selectById(id);
        }catch(Exception e){
            log.error("delete error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "creative", allEntries = true)
    public ReturnResult update(Creative creative){
        try{
            creativeMapper.updateById(creative);
        }catch (Exception e){
            log.error("update error",e);
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }
    @Cacheable(value ="creative" ,keyGenerator ="wrapperKey" )
    public ReturnResult select(Wrapper wrapper,Integer limit,Integer offset,String sort,String order){
        Map<String,Object> mapData = new HashMap<>();
        Page<Creative> page = new PageFactory<Creative>().defaultPage();
        try {
            page.setRecords(creativeMapper.selectPage(page,wrapper));
        }catch (Exception e){
            log.error("select error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        mapData.put("size", page.getTotal());
        mapData.put("data", page.getRecords());
        return new ReturnResult(ResultUtil.SUCCESS_CODE,mapData);
    }

    @Transactional
    public ReturnResult deleteByCampaignId(Long id) {
        EntityWrapper<Creative> wrapper = new EntityWrapper();
        wrapper.eq("campaign_id",id);
        try{
            creativeMapper.delete(wrapper);
        }catch (Exception e){
            log.error("deleteByCampaignId error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }
}
