package com.baby.work.mapper;

import com.baby.work.pojo.SystemDictionary;
import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.utils.SystemDictionaryItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface SystemDictionaryMapper extends BaseMapper<SystemDictionary> {

    @Select("SELECT d.`code` code, i.`id` id,i.`parent_id` parentId,i.`value` value,i.`order_no` orderNo,i.`create_time` createTime FROM t_system_dictionary d ,t_system_dictionary_item i where d.`id` = i.`parent_id`")
    List<SystemDictionaryItemVo> getDict();
}
