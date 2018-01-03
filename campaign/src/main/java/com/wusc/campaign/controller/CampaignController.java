
package com.wusc.campaign.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.dto.CampaignDTO;
import com.wusc.campaign.model.Campaign;
import com.wusc.campaign.service.CampaignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * create by wusc on 2017/12/21
 */
@RestController
@RequestMapping(headers = "Accept=application/json;",produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @ApiOperation(value="添加活动", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "campaign",method = RequestMethod.POST)
    public ReturnResult add(@Valid @RequestBody CampaignDTO param){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(param,campaign);
        return campaignService.add(campaign);
    }

    @ApiOperation(value="删除活动", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "campaign",method = RequestMethod.DELETE)
    public ReturnResult delete(@PathVariable(required=true) Long id) {
        return campaignService.delete(id);
    }


    @ApiOperation(value="更新活动", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "campaign",method = RequestMethod.PUT)
    public ReturnResult update(@PathVariable(required=true) Long id, @Valid @RequestBody CampaignDTO param){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(param,campaign);
        campaign.setId(id);
        return campaignService.update(campaign);
    }


    @ApiOperation(value="查询活动", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="Integer", paramType="query", value="每页多少", name="limit", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="偏移量", name="offset", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序字段", name="sort", required=false),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序规则", name="order", required=false),
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "campaign",method = RequestMethod.GET)
    public ReturnResult select(Long id,String name,Integer limit,Integer offset,String sort,String order){
        Wrapper<Campaign> wrapper = new EntityWrapper<>();
        if (id!=null){
            wrapper.eq("id",id);
        }
        if (name!=null){
            wrapper.like("name",name);
        }
        return campaignService.select(wrapper,limit,offset,sort,order);


    }

}

