package com.baby.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Adorez
 * @Date: 2020/2/10 16:52
 * @Description: 结果封装类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result {

    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> result=new HashMap<>();
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        Result Result = new Result();
        Result.setCode(ResponseCode.SUCCESS.getCode());
        Result.setMsg(ResponseCode.SUCCESS.getMessage());
        Result.setSuccess(ResponseCode.SUCCESS.getSuccess());
        return Result;
    }

    public static Result success(Map<String, Object> data) {
        Result Result = new Result();
        Result.setCode(ResponseCode.SUCCESS.getCode());
        Result.setResult(data);
        Result.setSuccess(ResponseCode.SUCCESS.getSuccess());
        return Result;
    }

    public static Result failure() {
        Result Result = new Result();
        Result.setCode(ResponseCode.UNKNOWN_REASON.getCode());
        Result.setMsg(ResponseCode.UNKNOWN_REASON.getMessage());
        Result.setSuccess(ResponseCode.UNKNOWN_REASON.getSuccess());
        return Result;
    }

    public static Result failure(Integer code, String msg) {
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg(msg);
        Result.setSuccess(false);
        return Result;
    }
    public Result result(String key, Object value){
        this.result.put(key, value);
        return this;
    }
    public Result result(Map<String, Object> map){
        this.setResult(map);
        return this;
    }
}
