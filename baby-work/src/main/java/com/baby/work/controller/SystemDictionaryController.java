package com.baby.work.controller;


import com.baby.common.utils.Result;
import com.baby.work.pojo.SystemDictionary;
import com.baby.work.service.SystemDictionaryService;
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
@RequestMapping("/admin/systemDic")
@Api(tags = "数据字典组管理")
public class SystemDictionaryController {

    @Autowired
    private SystemDictionaryService systemService;

    @ApiOperation(value = "获取所有数据字典组")
    @PostMapping("/getInfo")
    public Result getAllDic(@ApiParam(name = "systemDictionaryVo", value = "查询对象") BasicVo basicVo){
        Page<SystemDictionary> page= new Page<>(basicVo.getNowPage(), basicVo.getPageSize());
        systemService.getInfo(page, basicVo);
        return Result.success().result("allCount",page.getTotal()).result("datalist",page.getRecords());
    }

    @ApiOperation(value ="新增或修改数据字典" )
    @PostMapping("/saveOrUpdateDic")
    public Result saveOrUpdateDic(@ApiParam(name = "systemDictionary", value = "数据字典对象")SystemDictionary systemDictionary){
        systemService.saveOrUpdate(systemDictionary);
        return Result.success();
    }

}

