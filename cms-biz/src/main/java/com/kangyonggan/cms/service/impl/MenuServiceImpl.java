package com.kangyonggan.cms.service.impl;

import com.kangyonggan.cms.mapper.MenuMapper;
import com.kangyonggan.cms.model.Menu;
import com.kangyonggan.cms.service.MenuService;
import com.kangyonggan.extra.core.annotation.Cache;
import com.kangyonggan.extra.core.annotation.Log;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Log
    @Cache(key = "menu:username:${username}")
    public List<Menu> findMenusByUsername(String username) {
        List<Menu> menus = menuMapper.selectMenusByUsername(username);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, "", 0L);
    }

    @Override
    @Log
    public boolean existsMenuCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return super.exists(menu);
    }

    @Override
    @Log
    @Cache(key = "menu:role:${code}")
    public List<Menu> findMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code);
    }

    @Override
    @Log
    @Cache(key = "menu:all")
    public List<Menu> findAllMenus() {
        Example example = new Example(Menu.class);
        example.setOrderByClause("sort asc");

        List<Menu> menus = myMapper.selectByExample(example);
        List<Menu> wrapList = new ArrayList();

        return recursionTreeList(menus, wrapList, "", 0L);
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                List<Menu> leaf = new ArrayList();
                menu.setLeaf(leaf);
                menu.setPid(pid);
                toList.add(menu);
                recursionList(from, leaf, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                menu.setPid(pid);
                toList.add(menu);
                recursionTreeList(from, toList, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

}
