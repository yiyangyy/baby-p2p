package com.baby.work.service;

import com.baby.work.pojo.SystemDictionary;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.utils.SystemDictionaryItemVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
public interface SystemDictionaryService extends IService<SystemDictionary> {
    public void getInfo(Page<SystemDictionary> page, BasicVo basicVo);

    /**
     * @Author LR
     * @Description 字典详情信息查询
     * @Date 22:58 2020/2/20
     * @Param []
     * @return java.util.List<com.baby.work.utils.SystemDictionaryItemVo>
     **/
    List<SystemDictionaryItemVo> getDict();
}
