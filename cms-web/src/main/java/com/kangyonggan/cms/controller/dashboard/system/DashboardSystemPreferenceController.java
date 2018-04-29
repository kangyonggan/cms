package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Page;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Response;
import com.kangyonggan.cms.model.Preference;
import com.kangyonggan.cms.service.PreferenceService;
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
@RequestMapping("dashboard/system/preference")
public class DashboardSystemPreferenceController extends BaseController {

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 偏好管理
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public String index() {
        int a = 1 / 0;
        return getPathList();
    }

    /**
     * 偏好列表查询
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Page<Preference> list() {
        Params params = getRequestParams();
        List<Preference> preferences = preferenceService.searchPreferences(params);

        return new Page<>(preferences);
    }

    /**
     * 编辑偏好
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("preference", preferenceService.findPreferenceById(id));
        return getPathFormModal();
    }

    /**
     * 更新偏好
     *
     * @param preference
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public Response update(@ModelAttribute("preference") @Valid Preference preference, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            preferenceService.updatePreference(preference);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Response remove(@PathVariable("id") Long id) {
        preferenceService.deletePreferenceById(id);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleted", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Response deleted(@RequestParam("ids") String ids) {
        preferenceService.deletePreferenceByIds(ids);
        return Response.getSuccessResponse();
    }

}
