package com.kangyonggan.cms.constants;

import lombok.Getter;

/**
 * 响应码枚举
 *
 * @author kangyonggan
 * @date 3/16/18
 */
public enum Resp {

    /**
     * 操作成功
     */
    SUCCESS("0000", "操作成功"),

    /**
     * 未知错误
     */
    FAILURE("9999", "未知错误，请联系管理员！");

    /**
     * 响应码
     */
    @Getter
    String respCo;

    /**
     * 响应消息
     */
    @Getter
    String respMsg;

    Resp(String respCo, String respMsg) {
        this.respCo = respCo;
        this.respMsg = respMsg;
    }

}
