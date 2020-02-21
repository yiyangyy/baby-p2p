package com.baby.work.mapper;

import com.baby.work.pojo.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
    public int add(Borrow borrow);

}
