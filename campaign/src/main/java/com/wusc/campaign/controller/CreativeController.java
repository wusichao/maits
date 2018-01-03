
package com.wusc.campaign.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.dto.CreativeDTO;
import com.wusc.campaign.model.Creative;
import com.wusc.campaign.service.CreativeService;
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
public class CreativeController {
    @Autowired
    private CreativeService creativeService;

    @ApiOperation(value="添加创意", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "creative",method = RequestMethod.POST)
    public ReturnResult add(@Valid @RequestBody CreativeDTO param){
        Creative creative = new Creative();
        BeanUtils.copyProperties(param,creative);
        return creativeService.add(creative);
    }
    @ApiOperation(value="删除创意", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "creative",method = RequestMethod.DELETE)
    public ReturnResult delete(@PathVariable(required=true) Long id) {
        return creativeService.delete(id);
    }

    @ApiOperation(value="更新创意", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "creative",method = RequestMethod.PUT)
    public ReturnResult update(@PathVariable(required=true) Long id,@Valid @RequestBody CreativeDTO param){
        Creative creative = new Creative();
        BeanUtils.copyProperties(param,creative);
        creative.setId(id);
        return creativeService.update(creative);
    }

    @ApiOperation(value="查询创意", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="Integer", paramType="query", value="每页多少", name="limit", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="偏移量", name="offset", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序字段", name="sort", required=false),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="排序规则", name="order", required=false),
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "creative",method = RequestMethod.GET)
    public ReturnResult select(Long id,String name,Integer limit,Integer offset,String sort,String order){
        Wrapper<Creative> wrapper = new EntityWrapper<>();
        if (id!=null){
            wrapper.eq("id",id);
        }
        if (name!=null){
            wrapper.like("name",name);
        }
        return creativeService.select(wrapper,limit,offset,sort,order);
    }
}

