package com.baby.work.service;

import com.baby.work.pojo.LoginLog;
import com.baby.work.query.LoginLogsQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface LoginLogService extends IService<LoginLog> {
    public void getLoginLogs(Page<LoginLog> page, LoginLogsQuery loginLogsQuery);
}
