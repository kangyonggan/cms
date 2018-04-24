package com.kangyonggan.cms.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author kangyonggan
 * @date 4/24/18
 */
public class AbstractFunctionTag implements TemplateMethodModelEx {

    /**
     * 参数一：方法名
     *
     * @param args
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object exec(List args) throws TemplateModelException {
        if (args.isEmpty()) {
            return null;
        }

        try {
            Method method = getClass().getDeclaredMethod(args.get(0).toString(), new Class[]{List.class});
            return method.invoke(this, new Object[]{args});
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }
}
