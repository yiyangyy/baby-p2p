package com.baby.work.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "undertakeorg对象",description = "承办机构对象")
@Data
public class BorrowQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "查询开始时间", example = "2019‐01‐01 10:10:10",required = false)
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间", example = "2019‐12‐01 10:10:10",required = false)
    private String end;
    @ApiModelProperty(value = "借款状态",required = false)
    private Integer borrowState;
}
