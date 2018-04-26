package com.kangyonggan.cms.freemarker;

import com.kangyonggan.cms.annotation.Enum;
import com.kangyonggan.cms.util.PackageUtil;
import com.kangyonggan.extra.core.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author kangyonggan
 * @date 2018/4/23 0023
 */
@Component
public class EnumTag extends AbstractFunctionTag {

    /**
     * 枚举所在的包
     */
    private static final String ENUM_BASE_PACKAGE = "com.kangyonggan.cms.constants";

    /**
     * 所有标有@Enum注解的枚举
     */
    private Map<String, Class<?>> classMap;

    /**
     * 所有的@Enum
     */
    private Map<String, Enum> enumMap;

    public EnumTag() {
        initEnums();
    }

    /**
     * 初始化枚举
     */
    private void initEnums() {
        classMap = new HashMap<>(16);
        enumMap = new HashMap<>(16);
        List<Class<?>> classList = PackageUtil.getClass(ENUM_BASE_PACKAGE);
        for (Class<?> clazz : classList) {
            Enum e = clazz.getDeclaredAnnotation(Enum.class);
            if (e != null) {
                String key = e.key();
                if (StringUtils.isEmpty(key)) {
                    key = StringUtil.firstToLowerCase(clazz.getSimpleName());
                }

                if (existKey(key)) {
                    throw new RuntimeException("@Enum注解使用错误，key=" + key + "已存在！");
                }
                classMap.put(key, clazz);
                enumMap.put(key, e);
            }
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    private boolean existKey(String key) {
        if (enumMap.containsKey(key)) {
            return true;
        }
        return false;
    }

    /**
     * 获取枚举的名值对, 根据key
     *
     * @param arguments
     * @return
     */
    public LinkedHashMap<Object, Object> map(List arguments) {
        if (!hasLessArgs(arguments, 2)) {
            throw new RuntimeException("获取枚举的名值对时必须制定枚举的key！");
        }
        String key = arguments.get(1).toString();
        Class<?> enumClass = classMap.get(key);
        Enum e = enumMap.get(key);
        if (enumClass == null || e == null) {
            throw new RuntimeException("获取枚举的名值对时异常，key=" + key + "不存在！");
        }

        LinkedHashMap<Object, Object> data = new LinkedHashMap<>();
        String code = e.code();
        String name = e.name();

        try {
            Object enumObj = enumClass.getEnumConstants()[0];
            Object codeObj = enumClass.getDeclaredMethod("get" + StringUtils.capitalize(code)).invoke(enumObj);
            Object nameObj = enumClass.getDeclaredMethod("get" + StringUtils.capitalize(name)).invoke(enumObj);

            data.put(codeObj, nameObj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return data;
    }

}
