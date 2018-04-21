package com.kangyonggan.cms.interceptor;

import com.kangyonggan.cms.controller.BaseController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @date 2018/4/21 0021
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        currentRequest.set(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        currentRequest.remove();
    }

    public static HttpServletRequest getRequest() {
        return currentRequest.get();
    }
}
