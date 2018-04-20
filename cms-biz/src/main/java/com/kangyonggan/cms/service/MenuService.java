package com.kangyonggan.cms.service;

import com.kangyonggan.cms.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
public interface MenuService {

    /**
     * 查找用户菜单
     *
     * @param username
     * @return
     */
    List<Menu> findMenusByUsername(String username);

    /**
     * 校验菜单代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsMenuCode(String code);

}
