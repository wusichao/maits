package com.wusc.campaign.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import com.wusc.campaign.dao.ChannelMapper;
import com.wusc.campaign.model.Channel;
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
 * create by wusc on 2017/12/20
 */

@Service
public class ChannelService {
    private static final Logger log = LoggerFactory.getLogger(ChannelService.class);
    @Autowired
    private ChannelMapper channelMapper;

    @Transactional
    @CacheEvict(value = "channel", allEntries = true)
    public ReturnResult add(Channel channel){
        try{
            channelMapper.insert(channel);
        }catch(Exception e){
            log.error("add error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "channel", allEntries = true)
    public ReturnResult delete(Long id){
        try{
            channelMapper.deleteById(id);
        }catch(Exception e){
            log.error("delete error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Transactional
    @CacheEvict(value = "channel", allEntries = true)
    public ReturnResult update(Channel channel){
        try{
            channelMapper.updateById(channel);
        }catch (Exception e){
            log.error("update error",e);
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Cacheable(value ="channel" ,keyGenerator ="wrapperKey" )
    public ReturnResult select(Wrapper<Channel> wrapper, Integer limit, Integer offset, String sort, String order){
        Map<String,Object> mapData = new HashMap<>();
        Page<Channel> page = new PageFactory<Channel>().defaultPage();
        try {
            page.setRecords(channelMapper.selectPage(page,wrapper));
        }catch (Exception e){
            log.error("select error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        mapData.put("size", page.getTotal());
        mapData.put("data", page.getRecords());
        return new ReturnResult(ResultUtil.SUCCESS_CODE,mapData);
    }

    public List<Channel> getChannelByIds(String channelIds) {

        return channelMapper.getChannelByIds(channelIds);
    }
}
