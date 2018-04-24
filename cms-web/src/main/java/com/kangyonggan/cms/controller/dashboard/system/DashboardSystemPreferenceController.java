package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Page;
import com.kangyonggan.cms.dto.Params;
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
import java.util.Map;

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
     * 添加偏好
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public String create(Model model) {
        model.addAttribute("preference", new Preference());
        return getPathFormModal();
    }

    /**
     * 保存偏好
     *
     * @param preference
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public Map<String, Object> save(@ModelAttribute("preference") @Valid Preference preference, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            preferenceService.savePreference(preference);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
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
    public Map<String, Object> update(@ModelAttribute("preference") @Valid Preference preference, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            preferenceService.updatePreference(preference);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
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
    public Map<String, Object> remove(@PathVariable("id") Long id) {
        preferenceService.deletePreferenceById(id);
        return super.getResultMap();
    }

}
