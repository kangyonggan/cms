package com.kangyonggan.cms.service;

import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.Preference;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/23/18
 */
public interface PreferenceService {

    /**
     * 根据偏好类型和用户名查找偏好
     *
     * @param type
     * @param username
     * @return
     */
    List<Preference> findPreferencesByTypeAndUsername(String type, String username);

    /**
     * 根据偏好类型、偏好名称和用户名查找偏好
     *
     * @param type
     * @param name
     * @param username
     * @return
     */
    Preference findPreferenceByTypeNameAndUsername(String type, String name, String username);

    /**
     * 搜索偏好
     *
     * @param params
     * @return
     */
    List<Preference> searchPreferences(Params params);

    /**
     * 查找偏好
     *
     * @param id
     * @return
     */
    Preference findPreferenceById(Long id);

    /**
     * 更新偏好
     *
     * @param preference
     */
    void updatePreference(Preference preference);

    /**
     * 删除偏好
     *
     * @param id
     */
    void deletePreferenceById(Long id);

    /**
     * 更新或者保存偏好
     *
     * @param type
     * @param name
     * @param value
     */
    void updateOrSavePreference(String type, String name, String value);
}
