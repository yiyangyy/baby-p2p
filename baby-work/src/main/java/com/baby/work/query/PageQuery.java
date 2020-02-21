package com.baby.work.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(value = "undertakeorg对象",description = "承办机构对象")
@Data
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;
    @ApiModelProperty(value = "nowPage")
    private Integer nowPage;
    @ApiModelProperty(value = "id",required = true)
    private String id;
}
