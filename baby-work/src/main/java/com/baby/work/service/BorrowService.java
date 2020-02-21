package com.baby.work.service;

import com.baby.work.pojo.Borrow;

import com.baby.work.query.BorrowQuery;

import com.baby.work.vo.BasicVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface BorrowService extends IService<Borrow> {
void qryBorrowInfoPage(Page<Borrow> pageParam, BorrowQuery borrowQuery);
    public int add(Borrow borrow);
    public void getBorrowInfo(Page<Borrow> page,BasicVo basicVo);
}
