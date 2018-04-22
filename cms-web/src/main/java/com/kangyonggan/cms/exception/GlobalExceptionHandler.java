package com.kangyonggan.cms.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @date 2018/4/22 0022
 */
//@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * 500
     *
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String error500(HttpServletRequest request, HttpServletResponse response, Exception e){
        String requestType = request.getHeader("X-Requested-With");
        response.setStatus(200);
        if (e instanceof UnauthorizedException) {
            log.info("权限不足以访问:{}", request.getRequestURI());
            return "redirect:/403";
        }
        if(requestType == null){
            return "redirect:/error#500";
        }
        return "redirect:/500";
    }

}
