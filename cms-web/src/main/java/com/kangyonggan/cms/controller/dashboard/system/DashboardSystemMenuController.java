package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.annotation.Token;
import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Response;
import com.kangyonggan.cms.model.Menu;
import com.kangyonggan.cms.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @date 2017/1/9
 */
@Controller
@RequestMapping("dashboard/system/menu")
public class DashboardSystemMenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    public String index(Model model) {
        List<Menu> allMenus = menuService.findAllMenus();

        model.addAttribute("allMenus", allMenus);
        return getPathIndex();
    }

    /**
     * 添加菜单
     *
     * @param pcode
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    @Token(key = "createMenu")
    public String create(@RequestParam(value = "pcode", defaultValue = "") String pcode,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parentMenu", menuService.findMenuByCode(pcode));
        return getPathFormModal();
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "createMenu", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            menuService.saveMenu(menu);
        } else {
            response.failure();
        }
        return response;
    }

    /**
     * 编辑菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    @Token(key = "editMenu")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuService.findMenuById(id);

        model.addAttribute("menu", menu);
        model.addAttribute("parentMenu", menuService.findMenuByCode(menu.getPcode()));
        return getPathFormModal();
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "editMenu", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Response response = Response.getSuccessResponse();

        if (!result.hasErrors()) {
            menuService.updateMenu(menu);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    public Response delete(@ModelAttribute("menu") Menu menu) {
        menuService.deleteMenu(menu);
        return Response.getSuccessResponse();
    }

}
