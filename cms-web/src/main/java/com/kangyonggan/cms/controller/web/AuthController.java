package com.kangyonggan.cms.controller.web;

import com.kangyonggan.cms.constants.AppConstants;
import com.kangyonggan.cms.constants.Resp;
import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.LoginLogService;
import com.kangyonggan.cms.util.IpUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Controller
@RequestMapping("auth")
@Log4j2
public class AuthController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录模板
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String layout() {
        return getPathRoot() + "/auth-layout";
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return getPathRoot() + "/index";
    }

    /**
     * 登录
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestParam(value = "captcha") String captcha, User user, HttpServletRequest request) {
        Map<String, Object> resultMap = getResultMap();

        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(AppConstants.KEY_CAPTCHA);
        log.info("session中的验证码为：{}", realCaptcha);
        log.info("上送的验证码为：{}", captcha);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            resultMap.put(AppConstants.RESP_CO, Resp.FAILURE.getRespCo());
            resultMap.put(AppConstants.RESP_MSG, "验证码错误或已失效");
            return resultMap;
        }

        // 清除验证码
        session.removeAttribute(AppConstants.KEY_CAPTCHA);

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        final Subject subject = SecurityUtils.getSubject();

        try {
            // 30天
            session.setMaxInactiveInterval(30 * 24 * 60 * 60);
            subject.login(token);
        } catch (UnknownAccountException uae) {
            log.warn("用户名不存在", uae);
            setResultMapFailure(resultMap, "用户名不存在");
            return resultMap;
        } catch (IncorrectCredentialsException ice) {
            log.warn("密码错误", ice);
            setResultMapFailure(resultMap, "密码错误");
            return resultMap;
        } catch (DisabledAccountException dae) {
            log.warn("账号已禁用", dae);
            setResultMapFailure(resultMap, "账号已禁用, 请联系管理员");
            return resultMap;
        } catch (Exception e) {
            log.error("未知异常", e);
            setResultMapFailure(resultMap);
            return resultMap;
        }

        // 保存登录日志
        loginLogService.saveLoginLog(user.getUsername(), IpUtil.getIp(request));
        return resultMap;
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        final Subject subject = SecurityUtils.getSubject();
        log.info("logout {}", subject.getPrincipal());
        subject.logout();
        return "redirect:/auth";
    }

}
