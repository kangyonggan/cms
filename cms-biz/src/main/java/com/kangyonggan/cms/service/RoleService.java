package com.kangyonggan.cms.service;

import com.kangyonggan.cms.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
public interface RoleService {

    /**
     * 查找用户角色
     *
     * @param username
     * @return
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 校验角色代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsRoleCode(String code);
}
