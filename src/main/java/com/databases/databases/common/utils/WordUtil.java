package com.databases.databases.common.utils;
//
//import com.aspose.words.Document;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordUtil {

    private static final String LICENSE = "<License>\n" +
            "   <Data>\n" +
            "       <Products>\n" +
            "           <Product>Aspose.Total for Java</Product>\n" +
            "           <Product>Aspose.Words for Java</Product>\n" +
            "       </Products>\n" +
            "       <EditionType>Enterprise</EditionType>\n" +
            "       <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
            "       <LicenseExpiry>20991231</LicenseExpiry>\n" +
            "       <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
            "   </Data>\n" +
            "   <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
            "</License>";

    private static boolean getLicense() {
//        try {
//            License asposeLic = new License();
//            asposeLic.setLicense(new ByteArrayInputStream(LICENSE.getBytes("UTF-8")));
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return false;
    }

    public static boolean getPdf(String srcWord, String distPdf, String srcContentType) {
//        FileOutputStream outputStream = null;
//        if (!getLicense()) {
//            return false;
//        }
//
//        try {
//            if (srcContentType.equals("application/pdf")) {
//                FileUtils.copyFile(new File(srcWord), new File(distPdf));
//            } else {
//                Document document = new Document(new FileInputStream(srcWord));
//                outputStream = new FileOutputStream(distPdf);
//                document.save(outputStream, SaveFormat.PDF);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return false;
    }
}
