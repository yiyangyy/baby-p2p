package com.baby.common.exception;

import com.baby.common.utils.ResponseCode;
import io.swagger.annotations.ApiModelProperty;

public class P2PException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    public P2PException(ResponseCode pageLimitFaild) {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 接受状态码和消息
     * @param code
     * @param message
     */
    public P2PException(Integer code, String message) {
        super(message);
        this.code=code;
    }


}
