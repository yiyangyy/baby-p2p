package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.LoginLog;
import com.baby.work.pojo.UserAccount;
import com.baby.work.pojo.UserWallet;
import com.baby.work.service.LoginLogService;
import com.baby.work.service.UserAccountService;
import com.baby.work.service.UserWalletService;
import com.baby.work.utils.SHAUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.Date;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@RestController
@RequestMapping("user/useraccount")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserWalletService userWalletService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * @Author LR
     * @Description 用户注册
     * @Date 15:36 2020/2/14
     * @Param [username, password]
     * @return void
     **/
    @PostMapping("register")
    public Result register(String username, String password){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        //System.err.println(password);
        //password = SHAUtils.encodeData(password);
        userAccount.setPassword(password);
        userAccount.setAccountStatus(1);
        userAccount.setAccountType(1);
        userAccount.setFillUserinfo(0);
        userAccountService.save(userAccount);
        UserWallet userWallet = new UserWallet();
        userWallet.setAccountId(userAccount.getId());
        userWallet.setAvailableAmount(new Long(10000));
        userWallet.setFreezeAmount(new Long(0));
        userWallet.setInterestPending(new Long(0));
        userWallet.setPrincipalPending(new Long(0));
        userWallet.setRepaidAmount(new Long(0));
        userWallet.setCreditScore(0);
        userWallet.setCreditLine(new Long(0));
        userWallet.setResidualCreditLine(new Long(0));
        userWalletService.save(userWallet);
        return Result.success();
    }

    /**
     * @Author LR
     * @Description 2.4检查用户名
     * @Date 14:27 2020/2/14
     * @Param [username]
     * @return boolean
     **/
    @PostMapping("/checkusername")
    public boolean checkUsername(String username){
        boolean con = userAccountService.checkUsername(username);
        return con;
    }

    /**
     * @Author LR
     * @Description 2.3获取账户信息
     * @Date 15:11 2020/2/14
     * @Param [id]
     * @return com.baby.common.utils.Result
     **/
    @PostMapping ("/get/{id}")
    public Result getAccount(@PathVariable String id){
        UserAccount userAccount = userAccountService.getById(id);
        if (userAccount != null){
            return Result.success().result("data" , userAccount);
        }
        return Result.failure();
    }

    /**
     * @Author LR
     * @Description 登录
     * @Date 11:03 2020/2/18
     * @Param [username, password, accountType]
     * @return com.baby.common.utils.Result
     **/
    @PostMapping("/login")
    public Result login(String username, String password, Integer accountType){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setAccountType(accountType);
        UserAccount user = userAccountService.getByUserAccount(userAccount);
        //ip获取
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localip=ia.getHostAddress();
            System.out.println("本机的ip是 ："+localip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginLog loginLog = new LoginLog();
        if (user != null){
            userAccount.setId(user.getId());
            userAccount.setLastLoginTime(new Date());
            userAccountService.updateById(userAccount);
            loginLog.setIp(ia.getHostAddress());
            loginLog.setLoginTime(new Date());
            loginLog.setUsername(username);
            loginLog.setLoginResult(1);
            loginLog.setAccountType(accountType);
            loginLogService.save(loginLog);
            return Result.success().result("data",user);
        }
        loginLog.setIp(ia.getHostAddress());
        loginLog.setLoginTime(new Date());
        loginLog.setUsername(username);
        loginLog.setLoginResult(0);
        loginLog.setAccountType(accountType);
        loginLogService.save(loginLog);
        return Result.failure();
    }
}

