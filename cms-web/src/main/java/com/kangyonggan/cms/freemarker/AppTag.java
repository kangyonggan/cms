package com.kangyonggan.cms.freemarker;

import com.kangyonggan.cms.util.PropertiesUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author kangyonggan
 * @date 2018/4/23 0023
 */
@Component
public class AppTag extends AbstractFunctionTag {

    /**
     * 获取UUID
     *
     * @param arguments
     * @return
     */
    public String uuid(List arguments) {
        return PropertiesUtil.getAppName() + UUID.randomUUID().toString().replaceAll("-", "");
    }

}
