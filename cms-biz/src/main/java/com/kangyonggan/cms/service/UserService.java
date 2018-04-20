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
}
