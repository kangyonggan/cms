package com.kangyonggan.cms.constants;

/**
 * @author kangyonggan
 * @date 3/16/18
 */
public interface AppConstants {

    /**
     * 分页大小
     */
    int PAGE_SIZE = 10;

    /**
     * 响应码的key
     */
    String RESP_CO = "respCo";

    /**
     * 响应消息的key
     */
    String RESP_MSG = "respMsg";

    /**
     * Shiro常量
     */
    String HASH_ALGORITHM = "SHA-1";
    int HASH_INTERATIONS = 2;
    int SALT_SIZE = 8;

    /**
     * 把验证码存放在session中的key
     */
    String KEY_CAPTCHA = "key-captcha";

    /**
     * 默认角色
     */
    String DEFAULT_ROLE_CODE = "ROLE_USER";

    /**
     * 文件根路径的key
     */
    String FILE_PATH_ROOT = "file.root.path";

    /**
     * 文件上传路径
     */
    String FILE_UPLOAD = "upload/";

}
