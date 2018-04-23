package com.kangyonggan.cms.service;

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

}
