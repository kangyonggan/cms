package com.kangyonggan.cms.constants;

import com.kangyonggan.extra.core.annotation.Handle;
import com.kangyonggan.extra.core.handle.EnumHandle;
import com.kangyonggan.extra.core.model.EnumInfo;

/**
 * @author kangyonggan
 * @date 4/28/18
 */
@Handle(type = Handle.Type.ENUM)
public class MyEnumHandle extends EnumHandle {

    private static MyEnumHandle instance = new MyEnumHandle();

    private MyEnumHandle() {
    }

    public static EnumInfo getEnumInfo(String key) {
        return instance.get(key);
    }

    private static void collectionEnumInfo(String key, String code, String name, String clazz) {
        try {
            instance.put(key, new EnumInfo(key, code, name, Class.forName(clazz)));
        } catch (ClassNotFoundException e) {
        }
    }
}
