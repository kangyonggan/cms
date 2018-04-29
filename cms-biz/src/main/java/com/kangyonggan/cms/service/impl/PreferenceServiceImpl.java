package com.kangyonggan.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Query;
import com.kangyonggan.cms.model.Preference;
import com.kangyonggan.cms.service.PreferenceService;
import com.kangyonggan.cms.util.ShiroUtils;
import com.kangyonggan.cms.util.StringUtil;
import com.kangyonggan.extra.core.annotation.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @date 4/23/18
 */
@Service
public class PreferenceServiceImpl extends BaseService<Preference> implements PreferenceService {

    @Override
    @Log
    public List<Preference> findPreferencesByTypeAndUsername(String type, String username) {
        if (StringUtil.hasEmpty(type, username)) {
            return new ArrayList<>(0);
        }

        Preference preference = new Preference();
        preference.setType(type);
        preference.setUsername(username);

        return myMapper.select(preference);
    }

    @Override
    @Log
    public Preference findPreferenceByTypeNameAndUsername(String type, String name, String username) {
        if (StringUtil.hasEmpty(type, name, username)) {
            return null;
        }
        Preference preference = new Preference();
        preference.setType(type);
        preference.setName(name);
        preference.setUsername(username);

        return myMapper.selectOne(preference);
    }

    @Override
    public List<Preference> searchPreferences(Params params) {
        Example example = new Example(Preference.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String username = query.getString("username");
        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLikeString(username));
        }
        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }
        String name = query.getString("name");
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLikeString(name));
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
    public Preference findPreferenceById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    public void updatePreference(Preference preference) {
        myMapper.updateByPrimaryKeySelective(preference);
    }

    @Override
    @Log
    public void deletePreferenceById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    public void updateOrSavePreferences(String type, String names, String value) {
        String[] nameArr = names.split(",");
        for (String name : nameArr) {
            Preference preference = new Preference();
            preference.setValue(value);

            Example example = new Example(Preference.class);
            example.createCriteria().andEqualTo("type", type).andEqualTo("name", name);
            int count = myMapper.selectCountByExample(example);
            if (count > 0) {
                myMapper.updateByExampleSelective(preference, example);
            } else {
                preference.setType(type);
                preference.setName(name);
                preference.setUsername(ShiroUtils.getShiroUsername());
                myMapper.insertSelective(preference);
            }
        }
    }

    @Override
    @Log
    public void deletePreferenceByIds(String ids) {
        Example example = new Example(Preference.class);
        String[] arr = ids.split(",");
        example.createCriteria().andIn("id", Arrays.asList(arr));

        myMapper.deleteByExample(example);
    }
}
