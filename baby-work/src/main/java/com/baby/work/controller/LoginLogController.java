package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.LoginLog;
import com.baby.work.query.LoginLogsQuery;
import com.baby.work.service.LoginLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/system")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "获取登录日志")
    @PostMapping("/loginLogs")
    public Result getLoginLogs(LoginLogsQuery loginLogsQuery){
        Page<LoginLog> page=new Page<>(loginLogsQuery.getNowPage(),loginLogsQuery.getPageSize());
        loginLogService.getLoginLogs(page,loginLogsQuery);
        return  Result.success().result("datalist",page.getRecords()).result("allCount",page.getTotal());
    }
}

