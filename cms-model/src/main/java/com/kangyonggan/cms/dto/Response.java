package com.kangyonggan.cms.dto;

import com.kangyonggan.cms.constants.Resp;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author kangyonggan
 * @date 2018/4/27 0027
 */
public class Response extends HashMap<String, Object> implements Serializable {

    /**
     * 响应码的key
     */
    public static final String RESP_CO = "respCo";

    /**
     * 响应消息的key
     */
    public static final String RESP_MSG = "respMsg";

    private Response() {

    }

    /**
     * 获取一个响应
     *
     * @return
     */
    public static Response getResponse() {
        return new Response();
    }

    /**
     * 获取一个成功的响应
     *
     * @return
     */
    public static Response getSuccessResponse() {
        Response response = new Response();
        response.put(RESP_CO, Resp.SUCCESS.getRespCo());
        response.put(RESP_MSG, Resp.SUCCESS.getRespMsg());
        return response;
    }

    /**
     * 获取一个失败的响应
     *
     * @return
     */
    public static Response getFailureResponse() {
        Response response = new Response();
        response.put(RESP_CO, Resp.FAILURE.getRespCo());
        response.put(RESP_MSG, Resp.FAILURE.getRespMsg());
        return response;
    }

    /**
     * 失败的响应
     */
    public Response failure() {
        failure(Resp.FAILURE.getRespMsg());
        return this;
    }

    /**
     * 失败的响应
     *
     * @param msg
     */
    public Response failure(String msg) {
        put(RESP_CO, Resp.FAILURE.getRespCo());
        put(RESP_MSG, msg);
        return this;
    }

}
