package com.kangyonggan.cms.interceptor;

import com.kangyonggan.cms.annotation.Token;
import com.kangyonggan.cms.util.RandomUtil;
import com.kangyonggan.cms.util.ShiroUtils;
import com.kangyonggan.cms.util.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @date 2018/4/21 0021
 */
@Log4j2
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 存放当前请求
     */
    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.CHECK) {
                if (isRepeatSubmit(request, token)) {
                    return false;
                }
            }
        }

        // 保存当前请求
        currentRequest.set(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.GENERATE) {
                String random = RandomUtil.getRandomString();
                modelAndView.addObject("_token", random);
                request.getSession().setAttribute(token.key(), random);
                log.info("{}生成一个token，key={}, value={}", ShiroUtils.getShiroUsername(), token.key(), random);
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 从本地线程中移除请求
        currentRequest.remove();
    }

    /**
     * 获取当前请求
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return currentRequest.get();
    }

    /**
     * 校验是否重复提交
     *
     * @param request
     * @param token
     * @return
     */
    private boolean isRepeatSubmit(HttpServletRequest request, Token token) {
        try {
            String random = request.getParameter("_token");
            String sessionRandom = (String) request.getSession().getAttribute(token.key());
            log.info("{}校验是否重复提交，key={}, random={}, sessionRandom={}", ShiroUtils.getShiroUsername(), token.key(), random, sessionRandom);
            if (StringUtil.hasEmpty(random, sessionRandom)) {
                return true;
            }
            return !random.equals(sessionRandom);
        } catch (Exception e) {
            log.error("校验是否重复提交异常", e);
            return true;
        } finally {
            request.getSession().removeAttribute(token.key());
        }
    }
}
