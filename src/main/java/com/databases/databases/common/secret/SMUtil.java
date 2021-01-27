package com.databases.databases.common.secret;

import com.databases.databases.common.secret.sm.SM3Digest;
import com.databases.databases.common.secret.sm.SM4Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;


public final class SMUtil {

    /**
     * <p>内部使用</p>
     * <p>SM3单向加密</p>
     * @param source
     * @return
     */
    public static String encodeSM3(String source) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        byte[] md = new byte[32];
        SM3Digest sm3 = new SM3Digest();

        sm3.update(source.getBytes(), 0, source.getBytes().length);
        sm3.doFinal(md, 0);

        String result = new String(Hex.encode(md));

        return result;
    }

    /**
     * <p>内部使用</p>
     * <p>SM4加密</p>
     * @param source
     * @return
     */
    public static String encodeSM4(String source) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        SM4Utils sm4 = new SM4Utils();

        sm4.setSecretKey(DigestUtils.md5Hex(new Key().getSM4Key()).substring(8, 24));
        sm4.setHexString(false);

        return sm4.encryptData_ECB(source);
    }

    /**
     * <p>内部使用</p>
     * <p>SM4解密</p>
     * @param source
     * @return
     */
    public static String decodeSM4(String source) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        SM4Utils sm4 = new SM4Utils();

        sm4.setSecretKey(DigestUtils.md5Hex(new Key().getSM4Key()).substring(8, 24));
        sm4.setHexString(false);

        return sm4.decryptData_ECB(source);
    }

    /**
     * <p>内部使用</p>
     * <p>Apptoken生成辅助方法</p>
     * @param source
     * @param key
     * @return
     */
    protected static String encodeSM4(String source, String key) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        SM4Utils sm4 = new SM4Utils();

        sm4.setSecretKey(key.substring(8, 24));
        sm4.setHexString(false);

        return sm4.encryptData_ECB(source);
    }

    /**
     * <p>内部使用</p>
     * <p>Apptoken生成辅助方法</p>
     * @param source
     * @param key
     * @return
     */
    protected static String decodeSM4(String source, String key) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        SM4Utils sm4 = new SM4Utils();

        sm4.setSecretKey(key.substring(8, 24));
        sm4.setHexString(false);

        return sm4.decryptData_ECB(source);
    }
}
