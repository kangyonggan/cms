package com.kangyonggan.cms.freemarker;

import com.kangyonggan.cms.util.RandomUtil;
import com.kangyonggan.shiro.SuperTag;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @date 2018/4/23 0023
 */
@Component
public class RandomDirective extends SuperTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException {
        env.getOut().write(RandomUtil.getRandomString("thFormat"));
    }
}
