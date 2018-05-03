package com.kangyonggan.cms.freemarker;

import com.kangyonggan.cms.constants.MyEnumHandle;
import com.kangyonggan.extra.core.model.EnumInfo;
import com.kangyonggan.freemarker.AbstractFunctionTag;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author kangyonggan
 * @date 2018/4/23 0023
 */
@Component
public class EnumTag extends AbstractFunctionTag {

    /**
     * 获取枚举的名值对, 根据key
     *
     * @param arguments
     * @return
     */
    public LinkedHashMap<Object, Object> map(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取枚举的名值对时必须指定枚举的key！");
        }
        String key = arguments.get(1).toString();
        EnumInfo enumInfo = MyEnumHandle.getEnumInfo(key);
        if (enumInfo == null) {
            throw new RuntimeException("获取枚举的名值对时异常，key=" + key + "不存在！");
        }

        try {
            return enumInfo.map();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
