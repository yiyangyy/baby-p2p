package com.baby.work.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Adorez
 * @Date: 2020/2/13 15:46
 * @Description:
 */
@Data
public class LoginLogsQuery {

    @ApiModelProperty(value = "开始时间")
    private String start;

    @ApiModelProperty(value = "结束时间")
    private String end;

    @ApiModelProperty(value = "登录结果(1:成功，0:失败)")
    private Integer loginResult;

    @ApiModelProperty(value = "账户类型(1:前台用户, 2:运营人员)")
    private Integer accountType;

    @ApiModelProperty(value = "用戶名")
    private String userName;

    @ApiModelProperty(value = "当前页面")
    private Integer nowPage;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
}
