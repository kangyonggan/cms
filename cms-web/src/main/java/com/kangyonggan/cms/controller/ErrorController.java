package com.kangyonggan.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @date 3/16/18
 */
//@Controller
//@RequestMapping
public class ErrorController extends BaseController {

    /**
     * 错误模板
     *
     * @return
     */
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String index() {
        return getPathRoot() + "/error-layout";
    }

    /**
     * 400
     *
     * @return
     */
    @RequestMapping(value = "400", method = RequestMethod.GET)
    public String error400() {
        return "redirect:/error#400";
    }

    /**
     * 403
     *
     * @return
     */
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String error403() {
        return "redirect:/error#403";
    }

    /**
     * 404
     *
     * @return
     */
    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String error404(HttpServletRequest request, HttpServletResponse response) {
        String requestType = request.getHeader("X-Requested-With");
        if(requestType != null){
            response.setStatus(200);
            return getPathRoot() + "/404";
        }
        return "redirect:/error#404";
    }

    /**
     * 500
     *
     * @return
     */
    @RequestMapping(value = "500", method = RequestMethod.GET)
    public String error500() {
        return "redirect:/error#500";
    }
}
