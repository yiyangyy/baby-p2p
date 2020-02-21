package com.baby.work.mapper;

import com.baby.work.pojo.UserWallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface UserWalletMapper extends BaseMapper<UserWallet> {
//当借款记录保存的时候，同时修改可用的借款余额
    public int residualCreditLine(UserWallet userWallet);
}
