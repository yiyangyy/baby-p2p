package com.baby.work.service.impl;

import com.baby.work.pojo.Borrow;
import com.baby.work.mapper.BorrowMapper;
import com.baby.work.query.BorrowQuery;
import com.baby.work.service.BorrowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import javax.swing.border.EmptyBorder;

/**
 * <p>
 *  服务实现类aaa
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Service
@Transactional
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
@Autowired
BorrowMapper borrowMapper;
    @Override
    public void qryBorrowInfoPage(Page<Borrow> pageParam, BorrowQuery borrowQuery) {
        System.out.println(borrowQuery+"1212121");
        QueryWrapper<Borrow> queryWrapper=new QueryWrapper<>();
        if (queryWrapper==null){
baseMapper.selectPage(pageParam,queryWrapper);
return;
        }
        String begin=borrowQuery.getBegin();
        String end=borrowQuery.getEnd();
        Integer borrowState=borrowQuery.getBorrowState();
if(!StringUtils.isEmpty(begin)){
queryWrapper.ge("apply_time",begin);
}
if (!StringUtils.isEmpty(end)){
    queryWrapper.le("apply_time",end);
}
if (!StringUtils.isEmpty(borrowState)){
    queryWrapper.like("borrow_state",borrowState);
}
baseMapper.selectPage(pageParam,queryWrapper);
    }

    @Override
    public int add(Borrow borrow) {
        return borrowMapper.add(borrow);
    }

    @Override
    public void getBorrowInfo(Page<Borrow> page,BasicVo basicVo) {
        QueryWrapper<Borrow> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("apply_time");

        String start = basicVo.getStart();
        String end = basicVo.getEnd();
        Integer state = basicVo.getState();

        if (!StringUtils.isEmpty(start)&!StringUtils.isEmpty(end)){
            queryWrapper.between("apply_time",start, end);
        }
        if (!StringUtils.isEmpty(state.toString())){
            if (state!=3){
                queryWrapper.eq("borrow_state",state);
            }
        }
        baseMapper.selectPage(page,queryWrapper);
    }
}



