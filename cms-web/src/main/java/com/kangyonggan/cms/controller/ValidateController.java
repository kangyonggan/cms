package com.kangyonggan.cms.controller;

import com.kangyonggan.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @date 2016/12/3
 */
@Controller
@RequestMapping("validate")
public class ValidateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 校验用户名是否可用
     *
     * @param username
     * @param oldUsername
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateUsername(@RequestParam("username") String username,
                                    @RequestParam(value = "oldUsername", required = false, defaultValue = "") String oldUsername) {
        if (username.equals(oldUsername)) {
            return true;
        }

        return !userService.existsUsername(username);
    }

    /**
     * 校验角色代码是否可用
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "role", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateRoleCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !roleService.existsRoleCode(code);
    }

    /**
     * 校验菜单代码是否可用
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "menu", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateMenuCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !menuService.existsMenuCode(code);
    }

    /**
     * 校验字典类型是否可用
     *
     * @param type
     * @param oldType
     * @return
     */
    @RequestMapping(value = "dictType", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateDictType(@RequestParam("type") String type,
                                    @RequestParam(value = "oldType", required = false, defaultValue = "") String oldType) {
        if (type.equals(oldType)) {
            return true;
        }

        return !dictionaryTypeService.existsDictionaryType(type);
    }

    /**
     * 校验字典是否可用
     *
     * @param type
     * @param oldType
     * @return
     */
    @RequestMapping(value = "dict", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateDict(@RequestParam("type") String type,
                                @RequestParam(value = "oldType", required = false, defaultValue = "") String oldType,
                                @RequestParam("code") String code,
                                @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (type.equals(oldType) && code.equals(oldCode)) {
            return true;
        }

        return !dictionaryService.existsDictionary(type, code);
    }

}