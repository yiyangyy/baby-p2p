package com.baby.common.utils;

import lombok.Getter;

/**
 * @Author: Adorez
 * @Date: 2020/1/1 21:25
 * @Description:
 */
@Getter
public enum ResponseCode {
    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    EXCEL_DATA_IMPORT_ERROR(false, 21005, "Excel数据导入错误"),
    USERLOGIN_INVALID_ERROR(false,21007,"登录信息过期，请重新登录"),
    USERUNLOGIN_ERROR(false, 21008,"用户未登录，请重新登录"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(false,21009,"回调失败"),
    FETCH_ACCEETOKEN_FAILD(false,21010,"accessToken获取失败"),
    PAGE_LIMIT_FAILD(false,21020,"分页参数有问题");
    private Boolean success;

    private Integer code;

    private String message;

    private ResponseCode(Boolean success, Integer code, String message)
    {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
