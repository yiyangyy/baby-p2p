package com.baby.work.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Adorez
 * @Date: 2020/2/13 9:53
 * @Description:
 */
@Data
@ApiModel(value="SystemDictionaryVo", description="字典组查询对象封装")
public class  BasicVo {

    @ApiModelProperty(value = "Id")
    private Integer id;

    @ApiModelProperty(value = "关键字")
    private String keyWord;

    @ApiModelProperty(value = "当前页面")
    private Integer nowPage=1;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize=5;

    @ApiModelProperty(value = "parentId")
    private Integer parentId;

    @ApiModelProperty(value = "开始时间")
    private String start;

    @ApiModelProperty(value = "结束时间")
    private String end;

    @ApiModelProperty(value = "状态")
    private Integer state;
}
