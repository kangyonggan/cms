package com.kangyonggan.cms.controller;

import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Query;
import com.kangyonggan.cms.interceptor.HandlerInterceptor;
import com.kangyonggan.cms.util.StringUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * @author kangyonggan
 * @date 2018/4/19 0019
 */
@Log4j2
public class BaseController {

    protected static final String DASHBOARD = "dashboard";
    private String pathRoot;
    private static final String LIST = "/list";
    private static final String INDEX = "/index";
    private static final String FORM = "/form";
    private static final String FORM_MODAL = "/form-modal";
    private static final String DETAIL = "/detail";
    private static final String DETAIL_MODAL = "/detail-modal";
    private static final String TABLE_TR = "/table-tr";

    public BaseController() {
        String className = getClass().getSimpleName();
        String[] arr = StringUtil.camelToArray(className);

        pathRoot = "";
        for (int i = 0; i < arr.length - 1; i++) {
            if (i != 0) {
                pathRoot += "/";
            }
            pathRoot += arr[i];
        }

        if (!pathRoot.startsWith(DASHBOARD)) {
            pathRoot = "web/" + pathRoot;
        }
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    protected Params getRequestParams() {
        Params params = new Params();

        // 分页相关参数
        params.setPageSize(getIntegerParam("limit", 10));
        int offset = getIntegerParam("offset", 0);
        params.setPageNum(offset / params.getPageSize() + 1);

        String sort = getStringParam("sort");
        params.setSort(StringUtil.convertCamelToUnderLine(sort));
        params.setOrder(getStringParam("order", "asc"));

        // 其他查询条件
        params.setQuery(getQuery());

        return params;
    }

    /**
     * 获取查询条件
     *
     * @return
     */
    protected Query getQuery() {
        return getQuery("query");
    }

    /**
     * 获取查询条件
     *
     * @param name
     * @return
     */
    protected Query getQuery(String name) {
        Query query = new Query();
        name += ".";
        Map<String, String[]> parameterMap = HandlerInterceptor.getRequest().getParameterMap();
        for (String key : parameterMap.keySet()) {
            if (key.startsWith(name)) {
                String[] value = parameterMap.get(key);
                if (value != null && value.length == 1) {
                    query.put(key.substring(name.length()), value[0]);
                } else {
                    query.put(key.substring(name.length()), value);
                }
            }
        }

        return query;
    }

    /**
     * 获取String类型的请求参数
     *
     * @param name
     * @return
     */
    protected String getStringParam(String name) {
        return HandlerInterceptor.getRequest().getParameter(name);
    }

    /**
     * 获取String类型的请求参数, 带默认值
     *
     * @param name
     * @param defaultValue
     * @return
     */
    protected String getStringParam(String name, String defaultValue) {
        String param = HandlerInterceptor.getRequest().getParameter(name);
        return param == null ? defaultValue : param;
    }

    /**
     * 获取int类型的请求参数
     *
     * @param name
     * @return
     */
    protected int getIntegerParam(String name) {
        return Integer.parseInt(HandlerInterceptor.getRequest().getParameter(name));
    }

    /**
     * 获取int类型的请求参数, 带默认值
     *
     * @param name
     * @param defaultValue
     * @return
     */
    protected int getIntegerParam(String name, int defaultValue) {
        try {
            return Integer.parseInt(HandlerInterceptor.getRequest().getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    protected String getPathRoot() {
        return pathRoot;
    }

    protected String getPathIndex() {
        return pathRoot + INDEX;
    }

    protected String getPathList() {
        return pathRoot + LIST;
    }

    protected String getPathForm() {
        return pathRoot + FORM;
    }

    protected String getPathDetail() {
        return pathRoot + DETAIL;
    }

    protected String getPathFormModal() {
        return pathRoot + FORM_MODAL;
    }

    protected String getPathDetailModal() {
        return pathRoot + DETAIL_MODAL;
    }

    protected String getPathTableTr() {
        return pathRoot + TABLE_TR;
    }
}
