package com.kangyonggan.cms.service.impl;

import com.kangyonggan.cms.model.Preference;
import com.kangyonggan.cms.service.PreferenceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/23/18
 */
@Service
public class PreferenceServiceImpl extends BaseService<Preference> implements PreferenceService {


    @Override
    public List<Preference> findPreferencesByTypeAndUsername(String type, String username) {
        Preference preference = new Preference();
        preference.setType(type);
        preference.setUsername(username);

        return myMapper.select(preference);
    }

    @Override
    public Preference findPreferenceByTypeNameAndUsername(String type, String name, String username) {
        Preference preference = new Preference();
        preference.setType(type);
        preference.setName(name);
        preference.setUsername(username);

        return myMapper.selectOne(preference);
    }
}
