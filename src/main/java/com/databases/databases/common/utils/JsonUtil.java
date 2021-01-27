package com.databases.databases.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


public final class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * <p>通过JavaBean获得JSON字符串</p>
     * @param object 原始JavaBean
     * @return 经过转换的JSON字符串
     */
    public static String getJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>通过JSON字符串获得JavaBean</p>
     * @param jsonString 原始JSON字符串
     * @param clazz 目标类型
     * @return 经过转换的JavaBean
     */
    public static Object getJavaBean(String jsonString, Class clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>ASCII转为String字符串，用于MQTT数据解析</p>
     * @param value 原始ASCII码
     * @return 经过转换的String字符串
     */
    public static String asciiToString(String value) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");

        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }

        return sbu.toString();
    }
}
