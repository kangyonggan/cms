package com.kangyonggan.cms.service.impl;

import com.kangyonggan.cms.constants.YesNo;
import com.kangyonggan.cms.mapper.RoleMapper;
import com.kangyonggan.cms.model.Role;
import com.kangyonggan.cms.service.RoleService;
import com.kangyonggan.extra.core.annotation.Cache;
import com.kangyonggan.extra.core.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Log
    @Cache(key = "role:username:${username}")
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

    @Override
    @Log
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return super.exists(role);
    }

    @Override
    @Log
    @Cache(key = "role:all")
    public List<Role> findAllRoles() {
        Role role = new Role();
        role.setIsDeleted(YesNo.NO.getValue());

        return myMapper.select(role);
    }
}
