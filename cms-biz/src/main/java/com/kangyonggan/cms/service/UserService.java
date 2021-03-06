package com.kangyonggan.cms.service;


import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 搜索用户
     *
     * @param params
     * @return
     */
    List<User> searchUsers(Params params);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUserByUsername(User user);

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    boolean existsUsername(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUserWithDefaultRole(User user);
}
