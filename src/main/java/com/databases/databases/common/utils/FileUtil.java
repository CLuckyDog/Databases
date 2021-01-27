package com.databases.databases.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class FileUtil {

    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static String getFileMd5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFile(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static LinkedList<String> splitFile(String srcFile, String destDir) {
        return splitFile(srcFile, destDir, 128);
    }

    public static LinkedList<String> splitFile(File srcFile, File destDir) {
        return splitFile(srcFile, destDir, 128);
    }

    public static LinkedList<String> splitFile(String srcFile, String destDir, int partSize) {
        File src = new File(srcFile);
        File dest = new File(destDir);
        return splitFile(src, dest, partSize);
    }

    public static LinkedList<String> splitFile(File srcFile, File destDir, int partSize) {

        LinkedList<String> fileList = new LinkedList<String>();

        byte[] buffer = new byte[partSize * 1024 * 1024];
        int partNumber = 0;
        int tempLength = 0;

        String fileMd5 = getFileMd5(srcFile);

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(srcFile);

            while ((tempLength = fileInputStream.read(buffer, 0, partSize * 1024 * 1024)) != -1) {
                fileOutputStream = new FileOutputStream(destDir.getAbsolutePath() + "/" + fileMd5 + "_" + partNumber);
                fileOutputStream.write(buffer, 0, tempLength);
                fileOutputStream.flush();

                fileList.add(destDir.getAbsolutePath() + "/" + fileMd5 + "_" + partNumber);
                partNumber += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileList;
    }

    public static String combineFile(String srcDir, LinkedList<String> srcFileList, String destDir) {
        return combineFile(srcDir, srcFileList, destDir, 128);
    }

    public static String combineFile(File srcDir, LinkedList<String> srcFileList, File destDir) {
        return combineFile(srcDir, srcFileList, destDir, 128);
    }

    public static String combineFile(String srcDir, LinkedList<String> srcFileList, String destDir, int partSize) {
        File src = new File(srcDir);
        File dest = new File(destDir);
        return combineFile(src, srcFileList, dest, partSize);
    }

    public static String combineFile(File srcDir, LinkedList<String> srcFileList, File destDir, int partSize) {

        if (srcFileList.size() > 0) {
            String filePath = destDir.getAbsolutePath();
            String fileName = srcFileList.get(0).split("_")[0];
            String finalFilePath = filePath + "/" + fileName;

            byte[] buffer = new byte[partSize * 1024 * 1024];
            FileOutputStream fileOutputStream = null;
            FileInputStream fileInputStream = null;

            try {
                fileOutputStream = new FileOutputStream(finalFilePath, true);
                for (String splitFile : srcFileList) {
                    int tempLength = 0;
                    fileInputStream = new FileInputStream(srcDir + "/" + splitFile);
                    while((tempLength = fileInputStream.read(buffer, 0, partSize * 1024 * 1024)) != -1) {
                        fileOutputStream.write(buffer, 0, tempLength);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return finalFilePath;
        }

        return null;
    }

    public static void main(String args[]) {
//        File file = new File("/home/wu_chenyang/文档/iso/oracle-6.9-V860937-01.iso");
//        LinkedList<String> list = splitFile(file, new File("/home/wu_chenyang/文档/iso/test"));
//        for (String path : list) {
//            System.out.println(path);
//        }

        LinkedList<String> list = new LinkedList<String>();
        list.add("44441a0b53ed4cf40572fc66195fe859_0");
        list.add("44441a0b53ed4cf40572fc66195fe859_1");
        list.add("44441a0b53ed4cf40572fc66195fe859_2");
        list.add("44441a0b53ed4cf40572fc66195fe859_3");
        list.add("44441a0b53ed4cf40572fc66195fe859_4");
        list.add("44441a0b53ed4cf40572fc66195fe859_5");
        list.add("44441a0b53ed4cf40572fc66195fe859_6");
        list.add("44441a0b53ed4cf40572fc66195fe859_7");
        list.add("44441a0b53ed4cf40572fc66195fe859_8");
        list.add("44441a0b53ed4cf40572fc66195fe859_9");
        list.add("44441a0b53ed4cf40572fc66195fe859_10");
        list.add("44441a0b53ed4cf40572fc66195fe859_11");
        list.add("44441a0b53ed4cf40572fc66195fe859_12");
        list.add("44441a0b53ed4cf40572fc66195fe859_13");
        list.add("44441a0b53ed4cf40572fc66195fe859_14");
        list.add("44441a0b53ed4cf40572fc66195fe859_15");
        list.add("44441a0b53ed4cf40572fc66195fe859_16");
        list.add("44441a0b53ed4cf40572fc66195fe859_17");
        list.add("44441a0b53ed4cf40572fc66195fe859_18");
        list.add("44441a0b53ed4cf40572fc66195fe859_19");
        list.add("44441a0b53ed4cf40572fc66195fe859_20");
        list.add("44441a0b53ed4cf40572fc66195fe859_21");
        list.add("44441a0b53ed4cf40572fc66195fe859_22");
        list.add("44441a0b53ed4cf40572fc66195fe859_23");
        list.add("44441a0b53ed4cf40572fc66195fe859_24");
        list.add("44441a0b53ed4cf40572fc66195fe859_25");
        list.add("44441a0b53ed4cf40572fc66195fe859_26");
        list.add("44441a0b53ed4cf40572fc66195fe859_27");
        list.add("44441a0b53ed4cf40572fc66195fe859_28");
        list.add("44441a0b53ed4cf40572fc66195fe859_29");
        list.add("44441a0b53ed4cf40572fc66195fe859_30");

        String s = combineFile("/home/wu_chenyang/文档/iso/test/", list, "/home/wu_chenyang/文档/iso/test/qqq");
        System.out.println(s);
    }
}
