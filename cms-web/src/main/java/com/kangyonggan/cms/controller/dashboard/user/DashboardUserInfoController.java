package com.kangyonggan.cms.controller.dashboard.user;

import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Response;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.UserService;
import com.kangyonggan.cms.util.FileUpload;
import com.kangyonggan.cms.util.ShiroUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @date 2017/1/8
 */
@Controller
@RequestMapping("dashboard/user/info")
public class DashboardUserInfoController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 基本信息
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("USER_INFO")
    public String info(Model model) {
        User user = userService.findUserByUsername(ShiroUtils.getShiroUsername());

        model.addAttribute("user", user);
        return getPathIndex();
    }

    /**
     * 基本信息
     *
     * @param user
     * @param result
     * @return
     * @throws FileUploadException
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("USER_INFO")
    public Response info(@ModelAttribute(value = "user") @Valid User user, BindingResult result,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        Response response = Response.getSuccessResponse();

        if (!result.hasErrors()) {
            user.setUsername(ShiroUtils.getShiroUsername());

            if (file != null && !file.isEmpty()) {
                String avatarPath = FileUpload.upload(file, "AVATAR");
                user.setAvatar(avatarPath);
            }

            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(null);
            }

            userService.updateUserByUsername(user);

            user = userService.findUserByUsername(user.getUsername());
            response.put("user", user);
        } else {
            response.failure();
        }

        return response;
    }

}
