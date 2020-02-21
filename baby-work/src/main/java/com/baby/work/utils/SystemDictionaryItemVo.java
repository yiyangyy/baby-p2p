package com.baby.work.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className SystemDictionaryItemVo
 * @Description 字典接收对象
 * @Author RJin
 * @Date 2020/2/20 22:46
 * @Version 1.0
 */
@Data
public class SystemDictionaryItemVo{
    @ApiModelProperty(value = "字典组编号")
    private String code;

    @ApiModelProperty(value = "字典项id")
    private Integer id;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "字典项值")
    private String value;

    @ApiModelProperty(value = "排序号(正序)")
    private Integer orderNo;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
