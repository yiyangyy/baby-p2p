package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.UserInfo;
import com.baby.work.service.SystemDictionaryService;
import com.baby.work.service.UserInfoService;
import com.baby.work.utils.SystemDictionaryItemVo;
import com.baby.work.utils.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/user/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SystemDictionaryService systemDictionaryService;

    /**
     * @Author LR
     * @Description 获取前台用户的基本信息
     * @Date 14:14 2020/2/14
     * @Param [id]
     * @return com.baby.common.utils.Result
     **/
    @PostMapping("/get/{accountId}")
    public Result getInfo(@PathVariable String accountId){
        UserInfo userInfo = userInfoService.getById(accountId);
        if (userInfo != null){
            return Result.success().result("data" , userInfo);
        }
        return Result.failure();
    }

    /**
     * @Author LR
     * @Description vo获取前台用户信息
     * @Date 14:14 2020/2/14
     * @Param [id]
     * @return com.baby.common.utils.Result
     **/
    @PostMapping("/getvo/{accountId}")
    public Result getInfoVo(@PathVariable String accountId){
        UserInfo userInfo = userInfoService.getById(accountId);
        if (userInfo != null){
            UserInfoVo userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(userInfo ,userInfoVo);
            List<SystemDictionaryItemVo> list = new ArrayList<>();
            list = systemDictionaryService.getDict();
            for (SystemDictionaryItemVo systemDictionaryItemVo:
                 list) {
                if (userInfo.getEduBackgroundId() == systemDictionaryItemVo.getOrderNo()
                        & systemDictionaryItemVo.getCode().indexOf("edu_background") != -1){
                    userInfoVo.setEduBackgroundValue(systemDictionaryItemVo.getValue());
                }
                if (userInfo.getIncomeLevelId() == systemDictionaryItemVo.getOrderNo()
                        & systemDictionaryItemVo.getCode().indexOf("income_level") != -1){
                    userInfoVo.setIncomeValue(systemDictionaryItemVo.getValue());
                }
                if (userInfo.getHouseConditionId() == systemDictionaryItemVo.getOrderNo()
                        & systemDictionaryItemVo.getCode().indexOf("house_condition") != -1){
                    userInfoVo.setHouseConditionValue(systemDictionaryItemVo.getValue());
                }
                if (userInfo.getMarriageId() == systemDictionaryItemVo.getOrderNo()
                        & systemDictionaryItemVo.getCode().indexOf("marriage") != -1){
                    userInfoVo.setMarriageValue(systemDictionaryItemVo.getValue());
                }
            }
            return Result.success().result("data" , userInfoVo);
        }
        return Result.failure();
    }
}

