package com.kangyonggan.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.cms.constants.MonitorType;
import com.kangyonggan.base.dto.Params;
import com.kangyonggan.base.dto.Query;
import com.kangyonggan.cms.model.Preference;
import com.kangyonggan.cms.service.PreferenceService;
import com.kangyonggan.cms.util.ShiroUtils;
import com.kangyonggan.base.util.StringUtil;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.extra.core.annotation.Monitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    @Monitor(type = MonitorType.UPDATE, description = "更新偏好type:${preference.type},name:${preference.name},value:${preference.value}")
    public void updatePreference(Preference preference) {
        myMapper.updateByPrimaryKeySelective(preference);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除偏好id:${id}")
    public void deletePreferenceById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新或插入偏好type:${type}，names=${names},value:${value}")
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
    @Monitor(type = MonitorType.DELETE, description = "批量删除偏好ids:${ids}")
    public void deletePreferenceByIds(String ids) {
        Example example = new Example(Preference.class);
        String[] arr = ids.split(",");
        example.createCriteria().andIn("id", Arrays.asList(arr));

        myMapper.deleteByExample(example);
    }
}
