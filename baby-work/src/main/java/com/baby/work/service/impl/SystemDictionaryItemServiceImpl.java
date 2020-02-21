package com.baby.work.service.impl;

import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.mapper.SystemDictionaryItemMapper;
import com.baby.work.service.SystemDictionaryItemService;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Service
public class SystemDictionaryItemServiceImpl extends ServiceImpl<SystemDictionaryItemMapper, SystemDictionaryItem> implements SystemDictionaryItemService {

    @Override
    public void getDetailInfo(Page<SystemDictionaryItem> page, BasicVo basicVo) {
        QueryWrapper<SystemDictionaryItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("order_no");
        Integer id = basicVo.getId();
        Integer parentId = basicVo.getParentId();
        String keyWord = basicVo.getKeyWord();
        if (!StringUtils.isEmpty(parentId.toString())){
            queryWrapper.eq("parent_id",parentId);
        }
        if (!StringUtils.isEmpty(keyWord)){
            queryWrapper.like("value",keyWord);
        }
        baseMapper.selectPage(page, queryWrapper);
    }
}
