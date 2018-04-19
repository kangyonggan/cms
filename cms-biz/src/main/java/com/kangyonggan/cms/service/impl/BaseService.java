package com.kangyonggan.cms.service.impl;

import com.kangyonggan.cms.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @date 16/5/23
 */
@Service
public abstract class BaseService<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    /**
     * 判断记录是否存在
     *
     * @param entity
     * @return
     */
    public boolean exists(T entity) {
        return myMapper.selectCount(entity) > 0;
    }

}

