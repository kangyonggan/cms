package com.kangyonggan.cms.constants;

import lombok.Getter;

/**
 * @author kangyonggan
 * @date 4/23/18
 */
public enum PreferenceType {

    ACE("ACE", "Ace Admin的偏好");

    @Getter
    private String type;

    @Getter
    private String name;

    PreferenceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
