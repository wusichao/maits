
package com.wusc.campaign.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.dto.ChannelDTO;
import com.wusc.campaign.model.Channel;
import com.wusc.campaign.service.ChannelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * create by wusc on 2017/12/20
 */
@RestController
@RequestMapping(headers = "Accept=application/json;",produces = MediaType.APPLICATION_JSON_VALUE)
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @ApiOperation(value="添加渠道", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "channel",method = RequestMethod.POST)
    public ReturnResult add(@Valid @RequestBody ChannelDTO param){
        Channel channel = new Channel();
        BeanUtils.copyProperties(param,channel);
        return channelService.add(channel);
    }

    @ApiOperation(value="删除渠道", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "channel",method = RequestMethod.DELETE)
    public ReturnResult delete(@PathVariable(required=true) Long id) {
        return channelService.delete(id);
    }

    @ApiOperation(value="更新渠道", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "channel",method = RequestMethod.PUT)
    public ReturnResult update(@PathVariable(required=true) Long id,@Valid @RequestBody ChannelDTO param){
        Channel channel = new Channel();
        BeanUtils.copyProperties(param,channel);
        channel.setId(id);
        return channelService.update(channel);
    }

    @ApiOperation(value="查询渠道", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="Integer", paramType="query", value="每页多少", name="limit", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="偏移量", name="offset", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序字段", name="sort", required=false),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序规则", name="order", required=false),
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "channel",method = RequestMethod.GET)
    public ReturnResult select(Long id,Long accountId,String name,Integer limit,Integer offset,String sort,String order){
        Wrapper<Channel> wrapper = new EntityWrapper<>();
        if (id!=null){
        wrapper.eq("id",id);
        }
        if (accountId!=null){
            wrapper.eq("account_id",accountId);
        }
        if (name!=null){
            wrapper.like("name",name);
        }
        return channelService.select(wrapper,limit,offset,sort,order);
    }
}

