package com.databases.databases.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class ConfigUtil {

    // 配置文件的路径
    private static final String CONFIG_NAME = "application.properties";
    private static Properties properties;

    /**
     * <p>获取配置文件中相关配置信息</p>
     * @param key 对应键值对的key值
     * @return 返回对应键值对的value值，如果不存在则返回默认空值
     */
    public static String getValue(String key) {

        if (properties == null) {
            init();
        }

        return properties.getProperty(key);
    }

    private static synchronized void init() {
        try {
            InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_NAME);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
