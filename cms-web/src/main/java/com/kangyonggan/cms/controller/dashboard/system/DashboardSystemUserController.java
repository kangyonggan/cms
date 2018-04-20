package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Page;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @date 2017/1/8
 */
@Controller
@RequestMapping("dashboard/system/user")
public class DashboardSystemUserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户管理
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String index() {
        return getPathList();
    }

    /**
     * 用户列表查询
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @ResponseBody
    public Page<User> list() {
        Params params = getRequestParams();
        List<User> users = userService.searchUsers(params);

        return new Page<>(users);
    }

    /**
     * 编辑用户
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathFormModal();
    }

    /**
     * 更新用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    public Map<String, Object> update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.updateUserByUsername(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return getPathFormModal();
    }

    /**
     * 保存用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    public Map<String, Object> save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.saveUserWithDefaultRole(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

}
