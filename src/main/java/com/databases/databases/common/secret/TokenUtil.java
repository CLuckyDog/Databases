package com.databases.databases.common.secret;


public final class TokenUtil {

    /**
     * <p>内部使用</p>
     * <p>根据账户uuid生成token</p>
     * @param accountUuid
     * @return
     */
    public static String getToken(String accountUuid) {

        if (accountUuid == null || accountUuid.isEmpty()) {
            return null;
        }

        String content = new Key().getAcsKey() + ":" + accountUuid;

        return SMUtil.encodeSM4(content);
    }

    /**
     * <p>内部使用</p>
     * <p>根据token反解析为token内容</p>
     * @param token
     * @return
     */
    public static String getContent(String token) {

        String content = SMUtil.decodeSM4(token);

        if (content == null || content.isEmpty()) {
            return null;
        }

        String[] contents = content.split(":");

        if (contents.length > 2) {
            return contents[1];
        }

        return null;
    }
}
