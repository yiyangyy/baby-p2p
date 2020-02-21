package com.baby.work.service;

import com.baby.work.pojo.Recharge;
import com.baby.work.vo.BasicVo;
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
public interface RechargeService extends IService<Recharge> {
    public void getRechargeInfo(Page<Recharge> page, BasicVo basicVo);
}
