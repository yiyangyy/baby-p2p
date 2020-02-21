package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.service.SystemDictionaryItemService;
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
@RequestMapping("/admin/systemDic/detail")
@Api(tags = "字典明细管理")
public class SystemDictionaryItemController {

    @Autowired
    private SystemDictionaryItemService itemService;

    @ApiOperation(value = "获取字典明细")
    @PostMapping("/getDic")
    public Result getDetailDic(@ApiParam(name = "dictionaryVo", value = "数据字典对象") BasicVo dictionaryVo){
        Page<SystemDictionaryItem> page=new Page<>(dictionaryVo.getNowPage(),dictionaryVo.getPageSize());
        itemService.getDetailInfo(page,dictionaryVo);
        return Result.success().result("datalist",page.getRecords()).result("allCount",page.getTotal());
    }

    @ApiOperation(value = "新增或修改字典明細")
    @PostMapping("/saveDic")
    public Result saveOrUpdate(@ApiParam(name = "detailItem", value = "字典明细对象")SystemDictionaryItem detailItem){
        itemService.saveOrUpdate(detailItem);
        return  Result.success();
    }
}

