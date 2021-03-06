package com.wusc.campaign.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wusc.campaign.dao.AccountMapper;
import com.wusc.campaign.model.Account;
import com.wusc.campaign.mq.rabbitMQ.Sender;
import com.wusc.campaign.pojo.AccountToken;
import com.wusc.campaign.utils.PageFactory;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * create by wusc on 2017/12/20
 */

@Service
public class AccountService {
    private static final Logger log  = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private Sender sender;
    @Autowired
    private TokenService tokenService;


    /**
     * 新增用户
     * @param account
     * @return
     */

    @Transactional
    @CacheEvict(value = "account", allEntries = true)
    public ReturnResult add(Account account) {
        try{
            accountMapper.insert(account);
            sender.send("account.add",account);
        }catch (Exception e){
            log.error("add error",e);
        return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    /**
     * 更新用户
     * @param account
     * @return
     */

    @Transactional
    @CacheEvict(value = "account", allEntries = true)
    public ReturnResult update(Account account) {
        try{
            accountMapper.updateById(account);
            sender.send("account.update",account);
        }catch (Exception e){
            log.error("updata error",e);
        return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    /**
     *根据id删除用户
     * @param id
     * @return
     */

    @Transactional
    @CacheEvict(value = "account", allEntries = true)
    public ReturnResult delete(Long id){
        try{
            accountMapper.deleteById(id);
            sender.send("account.delete",id);
        }catch (Exception e){
            log.error("delete",e);
        return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    @Cacheable(value ="account" ,keyGenerator ="wrapperKey" )
    public ReturnResult select(EntityWrapper<Account> wrapper, Integer limit, Integer offset, String sort, String order){
        Map<String,Object> mapData = new HashMap<String,Object>();
        Page<Account> page = new PageFactory<Account>().defaultPage();
        try{
            page.setRecords(accountMapper.selectPage(page,wrapper));
        }catch (Exception e){
           log.error("select error",e);
           return ResultUtil.SYSTEM_ERROR;
        }
        mapData.put("size",page.getTotal());
        mapData.put("data",page.getRecords());
        return new ReturnResult(ResultUtil.SUCCESS_CODE,mapData);
    }

    public ReturnResult login(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        try{
           account=accountMapper.selectOne(account);
        }catch (Exception e){
            log.error("login selectOne error",e);
        }
        if (account==null){
            return ResultUtil.LOGIN_ERROR;
        }else{
            AccountToken accountToken = new AccountToken();
            BeanUtils.copyProperties(account,accountToken);
            return tokenService.login(accountToken);
        }

    }

    public ReturnResult logout(String token) {

        return tokenService.logout(token);
    }

    public ReturnResult refresh(String oldToken) {
        return tokenService.refresh(oldToken);
    }
}

