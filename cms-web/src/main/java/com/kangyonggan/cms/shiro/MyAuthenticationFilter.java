package com.kangyonggan.cms.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author kangyonggan
 * @date 2018/4/22 0022
 */
public abstract class MyAuthenticationFilter extends AccessControlFilter {
    public static final String DEFAULT_SUCCESS_URL = "/";
    private String successUrl = "/";

    public String getSuccessUrl() {
        return this.successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = this.getSubject(request, response);
        boolean isAuthenticated = subject.isAuthenticated();
        HttpServletRequest req = (HttpServletRequest) request;
        String requestType = req.getHeader("X-Requested-With");
        if(requestType != null){
            try {
                req.getRequestDispatcher("403").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }

    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.redirectToSavedRequest(request, response, this.getSuccessUrl());
    }
}
