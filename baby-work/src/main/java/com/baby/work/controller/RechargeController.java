package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.Recharge;
import com.baby.work.service.RechargeService;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/admin/recharge")
@Api(tags = "充值管理")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("/getChargeInfo")
    @ApiOperation(value = "获取充值记录")
    public Result getRechargeInfo(@ApiParam(name = "basicVo",value = "充值条件查询") BasicVo basicVo){
        Page<Recharge> page=new Page<>(basicVo.getNowPage(),basicVo.getPageSize());
        rechargeService.getRechargeInfo(page,basicVo);
        return Result.success().result("datalist",page.getRecords()).result("allCount",page.getTotal());
    }

    @PostMapping("/updateState")
    @ApiOperation(value = "获取充值记录")
    public Result updateState(@ApiParam(name = "recharge",value = "充值对象") Recharge recharge){
        boolean ifUpdate = rechargeService.update(recharge, null);
        if (ifUpdate) {
            return Result.success();
        }
        return Result.failure();
    }
}

