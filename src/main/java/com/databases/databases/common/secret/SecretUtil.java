package com.databases.databases.common.secret;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;


public final class SecretUtil {

    /**
     * <p>内部使用</p>
     * <p>根据APPName生成AppID</p>
     * @param appName
     * @return
     */
    public static String getAppId(String appName) {

        if (appName == null || appName.isEmpty()) {
            return null;
        }

        String source = UUID.randomUUID().toString() + ":" + appName;

        return DigestUtils.md5Hex(source);
    }

    /**
     * <p>内部使用</p>
     * <p>根据AppID生成App密钥</p>
     * @param appId
     * @param appName
     * @return
     */
    public static String getAppSecret(String appId, String appName) {

        if (appId == null || appId.isEmpty()
                || appName == null || appName.isEmpty()) {
            return null;
        }

        String source = appId + ":" + appName;

        return DigestUtils.md5Hex(source);
    }

    /**
     * <p>内部使用</p>
     * <p>根据ID和秘钥生成token</p>
     * @param appId
     * @param appName
     * @param appSecret
     * @return
     */
    public static String encodeAppToken(String appId, String appName, String appSecret) {

        if (appId == null || appId.isEmpty()
                || appName == null || appName.isEmpty()
                || appSecret == null || appSecret.isEmpty()) {
            return null;
        }

        String source = new Key().getApiKey() + ":" + appId + ":" + appName;

        return SMUtil.encodeSM4(source, appSecret);
    }

    /**
     * <p>内部使用</p>
     * <p>反解析token为ID和秘钥</p>
     * @param appToken
     * @param appSecret
     * @return
     */
    public static String[] decodeAppToken(String appToken, String appSecret) {

        if (appToken == null || appToken.isEmpty()
                || appSecret == null || appSecret.isEmpty()) {
            return null;
        }

        String[] decodeString = SMUtil.encodeSM4(appToken, appSecret).split(":");
        String[] result = new String[2];

        if (decodeString.length == 3) {
            result[0] = decodeString[1];
            result[1] = decodeString[2];
        }

        return result;
    }
}
