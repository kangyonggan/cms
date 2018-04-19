package com.kangyonggan.cms.util;

import com.kangyonggan.cms.dto.ShiroUser;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;

/**
 * @author kangyonggan
 * @date 2018/4/10 0010
 */
@Log4j2
public class ShiroUtils {

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    public static ShiroUser getShiroUser() {
        try {
            return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            log.warn("无法获取当前登录的用户", e);
            return new ShiroUser();
        }
    }

    /**
     * 获取当前登录的用户名
     *
     * @return
     */
    public static String getShiroUsername() {
        try {
            return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        } catch (Exception e) {
            log.warn("无法获取当前登录的用户名", e);
            return null;
        }
    }

}
