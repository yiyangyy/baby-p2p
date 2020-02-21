package com.baby.oss.controller;

import com.baby.common.utils.Result;
import com.baby.oss.service.FileService;
import com.baby.oss.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Adorez
 * @Date: 2020/2/10 16:50
 * @Description:
 */
@CrossOrigin
@Api(tags = "阿里云文件管理")
@RestController
@RequestMapping("/oss/upload")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("file")
    public Result upload(
            @ApiParam(name = "file",value = "文件",required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(name = "host",value = "文件上传路径",required = false)
            @RequestParam(value = "host",required = false) String host){
        System.out.println("文件正在上传");
        if(!StringUtils.isEmpty(host)) {
            ConstantPropertiesUtil.FILE_HOST=host;
        }
        String uploadUrl= fileService.upload(file);
        return Result.success().result("url",uploadUrl);
    }
}
