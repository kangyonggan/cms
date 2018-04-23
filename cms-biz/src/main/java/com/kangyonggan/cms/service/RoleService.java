package com.kangyonggan.cms.service;

import com.kangyonggan.cms.dto.Params;
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
     * 搜索角色
     *
     * @param params
     * @return
     */
    List<Role> searchRoles(Params params);

    /**
     * 校验角色代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsRoleCode(String code);

    /**
     * 查找所有角色
     *
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 保存角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 查找角色
     *
     * @param code
     * @return
     */
    Role findRoleByCode(String code);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param code
     */
    void deleteRoleByCode(String code);

    /**
     * 更新角色菜单
     *
     * @param code
     * @param menus
     */
    void updateRoleMenus(String code, String menus);

}
