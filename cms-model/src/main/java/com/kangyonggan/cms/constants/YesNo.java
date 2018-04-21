package com.kangyonggan.cms.constants;

import lombok.Getter;

/**
 * @author kangyonggan
 * @date 2018/4/19 0019
 */
public enum YesNo {
    /**
     * 是
     */
    YES((byte) 1),

    /**
     * 否
     */
    NO((byte) 0);

    @Getter
    private byte value;

    YesNo(byte value) {
        this.value = value;
    }

}
