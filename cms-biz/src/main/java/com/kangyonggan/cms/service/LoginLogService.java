package com.kangyonggan.cms.service;

/**
 * @author kangyonggan
 * @date 3/27/18
 */
public interface LoginLogService {

    /**
     * 保存登录日志
     *
     * @param username
     * @param ip
     */
    void saveLoginLog(String username, String ip);

}
