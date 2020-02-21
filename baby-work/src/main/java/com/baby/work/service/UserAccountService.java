package com.baby.work.service;

import com.baby.work.pojo.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface UserAccountService extends IService<UserAccount> {

    /**
     * @Author LR
     * @Description 姓名验证
     * @Date 11:06 2020/2/18
     * @Param [username]
     * @return boolean
     **/
    boolean checkUsername(String username);

    /**
     * @Author LR
     * @Description 根据用户实体类查找用户对象
     * @Date 11:07 2020/2/18
     * @Param [userAccount]
     * @return com.baby.work.pojo.UserAccount
     **/
    UserAccount getByUserAccount(UserAccount userAccount);
}
