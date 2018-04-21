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

    /**
     * 更新用户密码
     *
     * @param user
     */
    void updateUserPassword(User user);

    /**
     * 删除用户
     *
     * @param username
     */
    void deleteUserByUsername(String username);

    /**
     * 更新用户角色
     *
     * @param username
     * @param roles
     */
    void updateUserRoles(String username, String roles);
}
