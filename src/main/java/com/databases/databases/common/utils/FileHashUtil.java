package com.databases.databases.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public final class FileHashUtil {

    @Deprecated
    public static String getFileHash(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            return DigestUtils.sha256Hex(fileInputStream);
        } catch (IOException ex) {
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
