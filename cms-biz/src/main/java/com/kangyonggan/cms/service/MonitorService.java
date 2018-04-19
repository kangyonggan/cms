package com.kangyonggan.cms.service;

import com.kangyonggan.extra.core.model.MonitorInfo;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/30/18
 */
public interface MonitorService {

    /**
     * 保存监控信息
     *
     * @param monitorInfo
     */
    void saveMonitor(MonitorInfo monitorInfo);

}
