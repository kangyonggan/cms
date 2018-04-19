package com.kangyonggan.cms.util;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * app.properties工具类
 *
 * @author kangyonggan
 * @date 2016/12/6
 */
@Log4j2
public class PropertiesUtil {

    private static Properties props;
    private static String appName;

    static {
        refresh();
    }

    private PropertiesUtil() {
    }

    public static void refresh() {
        InputStream in = null;
        InputStreamReader isr = null;
        try {
            props = new Properties();
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties");
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            props.load(reader);

            String propertiesPath = props.getProperty("properties.path");
            log.info("本地配置文件路径:{}", propertiesPath);

            isr = new InputStreamReader(new FileInputStream(propertiesPath), "GBK");
            props.load(isr);
            appName = props.getProperty("app.shortname");
            log.info("本地配置文件加载完毕");
        } catch (IOException e) {
            log.error("配置文件加载失败", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("关闭输入流异常", e);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {
                    log.error("关闭输入流异常", e);
                }
            }
        }
    }

    /**
     * 读取配置文件
     *
     * @param in 配置文件的输入流
     * @return 编码
     * @throws Exception
     */
    public static Properties readProperties(InputStream in, String charset) throws Exception {
        Properties props = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(in, charset);
            props.load(reader);
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return props;
    }

    /**
     * 读取配置文件
     *
     * @param in 配置文件的输入流
     * @throws Exception
     */
    public static Properties readProperties(InputStream in) throws Exception {
        return readProperties(in, "UTF-8");
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @return 编码
     * @throws Exception
     */
    public static Properties readProperties(String fileName, String charset) throws Exception {
        return readProperties(new FileInputStream(fileName), charset);
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @return 返回配置的名值对
     * @throws Exception
     */
    public static Properties readProperties(String fileName) throws Exception {
        return readProperties(fileName, "UTF-8");
    }

    /**
     * 获取properties的值，默认值""
     *
     * @param name
     * @return
     */
    public static String getProperties(String name) {
        return getPropertiesWithDefault(name, "");
    }

    /**
     * 获取properties的值
     *
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    public static String getPropertiesWithDefault(String name, String defaultValue) {
        return props.getProperty(appName + "." + name, defaultValue);
    }

}

