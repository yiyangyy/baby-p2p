package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.UserWallet;
import com.baby.work.service.UserWalletService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/user/userwallet")
public class UserWalletController {
    @Autowired
    private UserWalletService userWalletService;

    @PostMapping("/get/{accountId}")
    public Result getInfo(@PathVariable String accountId){
        UserWallet userWallet = userWalletService.getById(accountId);
        if (userWallet != null){
            return Result.success().result("data" , userWallet);
        }
        return Result.failure();
    }
}

