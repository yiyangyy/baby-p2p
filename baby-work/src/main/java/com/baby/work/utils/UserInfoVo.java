package com.baby.work.utils;

import com.baby.work.pojo.UserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

/**
 * @className UserInfoVo
 * @Description 用户信息vo类
 * @Author RJin
 * @Date 2020/2/20 23:02
 * @Version 1.0
 */
@Data
public class UserInfoVo extends UserInfo {
    @ApiModelProperty(value = "年收入")
    private String incomeValue;

    @ApiModelProperty(value = "婚姻情况")
    private String marriageValue;

    @ApiModelProperty(value = "个人学历")
    private String eduBackgroundValue;

    @ApiModelProperty(value = "年收入")
    private String houseConditionValue;
}
