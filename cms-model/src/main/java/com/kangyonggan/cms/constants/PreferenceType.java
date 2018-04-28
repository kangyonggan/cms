package com.kangyonggan.cms.constants;

import com.kangyonggan.extra.core.annotation.Enum;
import lombok.Getter;

/**
 * @author kangyonggan
 * @date 4/23/18
 */
@Enum(code = "type", name = "name", description = "偏好类型枚举")
public enum PreferenceType {

    /**
     * ace偏好
     */
    ACE("ace", "Ace Admin的偏好");

    @Getter
    private String type;

    @Getter
    private String name;

    PreferenceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
