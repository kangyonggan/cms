package com.kangyonggan.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.cms.constants.MonitorType;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Query;
import com.kangyonggan.cms.model.Dictionary;
import com.kangyonggan.cms.service.DictionaryService;
import com.kangyonggan.cms.util.StringUtil;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.extra.core.annotation.Monitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/27/18
 */
@Service
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {
    @Override
    public List<Dictionary> searchDictionaries(Params params) {
        Example example = new Example(Dictionary.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }
        String code = query.getString("code");
        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        String value = query.getString("value");
        if (StringUtils.isNotEmpty(value)) {
            criteria.andEqualTo("value", value);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.INSERT, description = "保存字典:${dictionary.code}")
    public void saveDictionary(Dictionary dictionary) {
        myMapper.insertSelective(dictionary);
    }

    @Override
    @Log
    public Dictionary findDictionaryById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新字典:${dictionary.id}")
    public void updateDictionary(Dictionary dictionary) {
        myMapper.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除字典:${id}")
    public void deleteDictionaryById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    public boolean existsDictionary(String type, String code) {
        Dictionary dictionary = new Dictionary();
        dictionary.setType(type);
        dictionary.setCode(code);
        return super.exists(dictionary);
    }
}
