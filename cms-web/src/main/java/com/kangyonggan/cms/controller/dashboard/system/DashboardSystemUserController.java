package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Page;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

}
