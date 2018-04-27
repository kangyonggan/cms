package com.kangyonggan.cms.controller.dashboard;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.service.PreferenceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author kangyonggan
 * @date 2018/4/27 0027
 */
@RestController
@RequestMapping("dashboard/preference")
public class DashboardPreferenceController extends BaseController {

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 更新偏好
     *
     * @param type
     * @param name
     * @param value
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public Map<String, Object> update(@RequestParam("type") String type,
                                      @RequestParam("name") String name,
                                      @RequestParam("value") String value) {
        preferenceService.updateOrSavePreference(type, name, value);
        return getResultMap();
    }

}
