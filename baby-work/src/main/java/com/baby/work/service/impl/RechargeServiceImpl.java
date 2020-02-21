package com.baby.work.service.impl;

import com.baby.work.pojo.Recharge;
import com.baby.work.mapper.RechargeMapper;
import com.baby.work.service.RechargeService;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.QueryResult;
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
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

    @Override
    public void getRechargeInfo(Page<Recharge> page, BasicVo basicVo) {
        QueryWrapper<Recharge> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("recharge_time");

        String start = basicVo.getStart();
        String end = basicVo.getEnd();
        Integer state = basicVo.getState();

        if (!StringUtils.isEmpty(state.toString())){
            if (state!=3){
                queryWrapper.eq("state",state);
            }
        }
        if (!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)){
            queryWrapper.between("recharge_time",start,end);
        }
        baseMapper.selectPage(page,queryWrapper);
    }
}
