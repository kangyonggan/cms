package com.kangyonggan.cms.controller.dashboard;

import com.kangyonggan.cms.constants.PreferenceType;
import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.ShiroUser;
import com.kangyonggan.cms.model.Menu;
import com.kangyonggan.cms.model.Preference;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.MenuService;
import com.kangyonggan.cms.service.PreferenceService;
import com.kangyonggan.cms.service.UserService;
import com.kangyonggan.cms.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 工作台模板
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String layout(Model model) {
        ShiroUser shiroUser = ShiroUtils.getShiroUser();
        User user = userService.findUserByUsername(shiroUser.getUsername());
        List<Menu> menus = menuService.findMenusByUsername(shiroUser.getUsername());
        List<Preference> preferences = preferenceService.findPreferencesByTypeAndUsername(
                PreferenceType.ACE.getType(), ShiroUtils.getShiroUsername());

        model.addAttribute("_user", user);
        model.addAttribute("_menus", menus);
        model.addAttribute("_preferences", preferences);
        return getPathRoot() + "/layout";
    }

    /**
     * 工作台首页
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String index() {
        return getPathRoot() + "/index";
    }


}
