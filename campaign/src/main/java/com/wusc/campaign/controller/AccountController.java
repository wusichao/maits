package com.wusc.campaign.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wusc.auth.utils.ReturnResult;
import com.wusc.campaign.model.Account;
import com.wusc.campaign.dto.AccountDTO;
import com.wusc.campaign.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * create by wusc on 2017/12/20
 */
@RestController
@RefreshScope
@RequestMapping(headers = "Accept=application/json;",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;
    @Value("${from}")
    private String from;

    @RequestMapping(value = "from")
    public String from(){
        return from;
    }

    @ApiOperation(value="用户注册", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
        })
    @RequestMapping(value = "account",method = RequestMethod.POST)
    public ReturnResult add(@Valid @RequestBody AccountDTO param, BindingResult result){
        Account account = new Account();
        BeanUtils.copyProperties(param,account);
        return accountService.add(account);
    }

    @ApiOperation(value="用户更新", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "account/{id}",method=RequestMethod.PUT)
    public ReturnResult update(@PathVariable(required = true) Long id , @Valid @RequestBody AccountDTO param, BindingResult result){
        Account account = new Account();
        BeanUtils.copyProperties(param,account);
        account.setId(id);
        return accountService.update(account);
    }

    @ApiOperation(value="删除用户", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "account/{id}",method=RequestMethod.DELETE)
    public ReturnResult delete(@PathVariable(required =true) Long id){
        return accountService.delete(id);
    }

    @ApiOperation(value="用户查询", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="Integer", paramType="query", value="每页多少", name="limit", required=true),
            @ApiImplicitParam(dataType="Integer", paramType="query", value="偏移量", name="offset", required=true),
            @ApiImplicitParam(dataType="String", paramType="query", value="排序字段", name="sort", required=false),
            @ApiImplicitParam(dataType="String", paramType="query", value="排序规则", name="order", required=false),
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "account",method = RequestMethod.GET)
    public ReturnResult select(Long id,String email,Short type,String companyName,String vertical,String webSite,String contact,String cellphone,Integer limit,Integer offset,String sort,String order){
        EntityWrapper<Account> wrapper = new EntityWrapper();
        if(id!=null){
            wrapper.eq("id",id);
        }
        if(email!=null){
            wrapper.like("email",email);
        }
        if (type!=null){
            wrapper.eq("type",type);
        }
        if(companyName!=null){
            wrapper.like("company_name",companyName);
        }
        if (vertical!=null){
            wrapper.eq("vertical",vertical);
        }
        if(webSite!=null){
            wrapper.eq("web_site",webSite);
        }
        if(contact!=null){
            wrapper.like("contact",contact);
        }
        if (cellphone!=null){
            wrapper.like("cellphone",cellphone);
        }
        return accountService.select(wrapper,limit,offset,sort,order);
    }

    //TODO validate email usable

    //TODO account info progress

}
