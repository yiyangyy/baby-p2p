package com.baby.work.service.impl;

import com.baby.work.pojo.LoginLog;
import com.baby.work.mapper.LoginLogMapper;
import com.baby.work.query.LoginLogsQuery;
import com.baby.work.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public void getLoginLogs(Page<LoginLog> page, LoginLogsQuery loginLogsQuery) {
        QueryWrapper<LoginLog> queryWrapper=new QueryWrapper<>();
        Integer accountType = loginLogsQuery.getAccountType();
        String username = loginLogsQuery.getUserName();
        Integer loginResult = loginLogsQuery.getLoginResult();
        String start = loginLogsQuery.getStart();
        String end = loginLogsQuery.getEnd();

        if (!StringUtils.isEmpty(accountType.toString())){
            if (accountType!=3){
                queryWrapper.eq("account_type",accountType);
            }
        }
        if (!StringUtils.isEmpty(loginResult.toString())){
            if (loginResult!=3){
                queryWrapper.eq("login_result",loginResult);
            }
        }
        if (!StringUtils.isEmpty(username)){
            queryWrapper.eq("username",username);
        }
        if (!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)){
            queryWrapper.between("login_time",start,end);
        }
        baseMapper.selectPage(page,queryWrapper);
    }
}
