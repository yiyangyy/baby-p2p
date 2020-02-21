package com.baby.work.service.impl;

import com.baby.work.pojo.UserAccount;
import com.baby.work.mapper.UserAccountMapper;
import com.baby.work.service.UserAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public boolean checkUsername(String username) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserAccount account = userAccountMapper.selectOne(queryWrapper);
        if (account == null){
            return false;
        }
        return true;
    }

    @Override
    public UserAccount getByUserAccount(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userAccount.getUsername());
        queryWrapper.eq("password", userAccount.getPassword());
        queryWrapper.eq("account_type", userAccount.getAccountType());
        UserAccount account = userAccountMapper.selectOne(queryWrapper);
        if (account == null){
            return null;
        }
        return account;
    }
}
