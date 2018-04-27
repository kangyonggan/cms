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
     * @param arguments
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.isEmpty()) {
            return null;
        }

        try {
            Method method = getClass().getDeclaredMethod(arguments.get(0).toString(), new Class[]{List.class});
            return method.invoke(this, new Object[]{arguments});
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }

    /**
     * 判断至少要有n个参数
     *
     * @param arguments
     * @param minLength
     * @return
     */
    protected boolean hasLessArgs(List arguments, int minLength) {
        return arguments.size() >= minLength;
    }

    /**
     * 判断至少要有2个参数
     *
     * @param arguments
     * @return
     */
    protected boolean hasLessTwoArgs(List arguments) {
        return arguments.size() >= 2;
    }

    /**
     * 判断至少要有3个参数
     *
     * @param arguments
     * @return
     */
    protected boolean hasLessThreeArgs(List arguments) {
        return arguments.size() >= 3;
    }

    /**
     * 判断至少要有4个参数
     *
     * @param arguments
     * @return
     */
    protected boolean hasLessFourArgs(List arguments) {
        return arguments.size() >= 4;
    }
}
