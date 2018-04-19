package com.kangyonggan.cms.util;

import com.kangyonggan.cms.constants.AppConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kangyonggan
 * @date 3/23/18
 */
public class RandomUtil {

    /**
     * 获取一个随机串
     *
     * @return
     */
    public static String getRandomString() {
        return getRandomString(StringUtils.EMPTY);
    }

    /**
     * 获取一个带有前缀的随机串
     *
     * @param prefix
     * @return
     */
    public static String getRandomString(String prefix) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        byte[] hash = Digests.sha1(StringUtils.EMPTY.getBytes(), salt, AppConstants.HASH_INTERATIONS);
        return prefix + DateUtil.getDate() + Encodes.encodeHex(hash);
    }

}
