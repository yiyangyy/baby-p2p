package com.baby.work.service;

import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface SystemDictionaryItemService extends IService<SystemDictionaryItem> {
    public void getDetailInfo(Page<SystemDictionaryItem> page, BasicVo basicVo);
}
