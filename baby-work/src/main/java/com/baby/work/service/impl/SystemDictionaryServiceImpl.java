package com.baby.work.service.impl;

import com.baby.work.pojo.SystemDictionary;
import com.baby.work.mapper.SystemDictionaryMapper;
import com.baby.work.pojo.SystemDictionaryItem;
import com.baby.work.service.SystemDictionaryService;
import com.baby.work.utils.SystemDictionaryItemVo;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@Service
public class SystemDictionaryServiceImpl extends ServiceImpl<SystemDictionaryMapper, SystemDictionary> implements SystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public List<SystemDictionaryItemVo> getDict() {
        return systemDictionaryMapper.getDict();
    }
    @Override
    public void getInfo(Page<SystemDictionary> page, BasicVo basicVo) {
        QueryWrapper<SystemDictionary> wrapper=new QueryWrapper<>();
        String keyWord = basicVo.getKeyWord();
        if (!StringUtils.isEmpty(keyWord)){
            wrapper.like("name",keyWord);
        }
        baseMapper.selectPage(page,wrapper);
    }
}
