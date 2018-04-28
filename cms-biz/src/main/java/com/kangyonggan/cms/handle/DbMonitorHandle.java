package com.kangyonggan.cms.handle;

import com.kangyonggan.cms.service.MonitorService;
import com.kangyonggan.cms.util.SpringUtils;
import com.kangyonggan.extra.core.annotation.Handle;
import com.kangyonggan.extra.core.handle.MonitorHandle;
import com.kangyonggan.extra.core.model.MonitorInfo;
import lombok.extern.log4j.Log4j2;

/**
 * @author kangyonggan
 * @date 3/30/18
 */
@Handle(type = Handle.Type.MONITOR)
@Log4j2
public class DbMonitorHandle implements MonitorHandle {

    private MonitorService monitorService;

    @Override
    public Object handle(MonitorInfo monitorInfo) {
        try {
            getMonitorService().saveMonitor(monitorInfo);
        } catch (Exception e) {
            log.error("保存监控信息异常", e);
        }
        return monitorInfo.getReturnValue();
    }

    private MonitorService getMonitorService() {
        if (monitorService == null) {
            monitorService = SpringUtils.getBean(MonitorService.class);
        }

        return monitorService;
    }

}

