package com.baby.work.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_system_dictionary_item")
@ApiModel(value="SystemDictionaryItem对象", description="字典明细表")
public class SystemDictionaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项id")
    @TableId(value = "id", type = IdType.AUTO)
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
