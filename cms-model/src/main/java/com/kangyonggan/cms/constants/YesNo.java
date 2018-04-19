package com.kangyonggan.cms.constants;

import lombok.Getter;

/**
 * @author kangyonggan
 * @date 2018/4/19 0019
 */
public enum YesNo {
    YES((byte) 1),
    NO((byte) 0);

    @Getter
    private byte value;

    YesNo(byte value) {
        this.value = value;
    }

}
