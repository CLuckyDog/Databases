package com.databases.databases.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


public class ExportExcelUtil<T> {

    public XSSFWorkbook exportExcel(List<List<LinkedHashMap<Integer[], String>>> mapListList, String[] headers, Collection<T> dataset, String fileName) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // 其它数据类型都当作字符串简单处理
                    if (value != null && value != "") {
                        textValue = value.toString();
                    }
                    if (textValue != null) {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
            workbook = getExportedFile(mapListList, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    public XSSFWorkbook exportOppo(List<List<LinkedHashMap<Integer[], String>>> mapListList, String[] headers, Collection<T> dataset, String fileName) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 5);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    if (0 < i && i < headers.length - 2) {
                        Field field = fields[1];
                        String fieldName = field.getName();
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        List<String> workingHoursList = (List<String>) getMethod.invoke(t, new Object[]{});
                        if (null != workingHoursList && workingHoursList.size() > 0) {
                            String workHour = workingHoursList.get(i - 1);
                            XSSFRichTextString richString = new XSSFRichTextString(workHour);
                            cell.setCellValue(richString);
                        }

                    } else {
                        Field field = null;
                        if (i == headers.length - 2){
                            field = fields[2];
                        } else if (i == headers.length - 1) {
                            field = fields[3];
                        } else {
                            field = fields[i];
                        }
                        String fieldName = field.getName();
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(t, new Object[]{});
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        // 其它数据类型都当作字符串简单处理
                        if (value != null && value != "") {
                            textValue = value.toString();
                        }
                        if (textValue != null) {
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }
                    }
                }
            }
            workbook = getExportedFile(mapListList, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    //产生输出
    public XSSFWorkbook getExportedFile(List<List<LinkedHashMap<Integer[], String>>> hashMapListList, XSSFWorkbook workbook) {
        Sheet sheet = ExcelUtil.getSheet(workbook, 0);
        if (null != sheet) {

            if (hashMapListList != null && hashMapListList.size() > 0) {
                for (List<LinkedHashMap<Integer[], String>> hashMapList : hashMapListList) {
                    for (LinkedHashMap<Integer[], String> hashMap : hashMapList) {
                        for (HashMap.Entry<Integer[], String> entry : hashMap.entrySet()) {
                            Integer[] integers = entry.getKey();
                            Integer x = integers[0];
                            Integer y = integers[1];
                            String annotate = entry.getValue();
                            // 创建绘图对象
                            XSSFDrawing xssfDrawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                            if (x != null && y != null) {
                                Cell cell = ExcelUtil.getCell(sheet, x, y);
                                if (null != cell && !StringUtils.isEmpty(annotate)) {
                                    // 获取批注对象
                                    // (int dx1, int dy1, int dx2, int dy2, short col1, int row1, short
                                    // col2, int row2)
                                    // 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
                                    XSSFComment comment = xssfDrawing.createCellComment(new XSSFClientAnchor(x, y, x, y, 3, 3, 5, 6));
                                    // 输入批注信息
                                    comment.setString(new XSSFRichTextString(annotate));
                                    // 添加作者,选中B5单元格,看状态栏
                                    comment.setAuthor("");
                                    // 将批注添加到单元格对象中
                                    cell.setCellComment(comment);
                                }
                            }
                        }
                    }
                }
            }
        }
        return workbook;
    }

    public XSSFWorkbook exportVivo(String[] headers, Collection<T> dataset, String fileName) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 5);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                //headers.length=40
                for (short i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    // fields.length = 10
                    if (i > (fields.length - 2)) {
                        Method getMethod = getMethod(fields, fields.length - 1, t);
                        if (null != getMethod) {
                            List<String> workingHoursList = (List<String>) getMethod.invoke(t, new Object[]{});
                            if (null != workingHoursList && workingHoursList.size() > 0) {
                                String workHour = workingHoursList.get(i - (fields.length - 1));
                                if ("0".equals(workHour)) {
                                    workHour = "";
                                }
                                XSSFRichTextString richString = new XSSFRichTextString(workHour);
                                cell.setCellValue(richString);
                            }
                        }
                    } else {
                        Method getMethod = getMethod(fields, i, t);
                        if (null != getMethod) {
                            Object value = getMethod.invoke(t, new Object[]{});
                            // 判断值的类型后进行强制类型转换
                            String textValue = null;
                            // 其它数据类型都当作字符串简单处理
                            if (value != null && value != "") {
                                textValue = value.toString();
                            }
                            if (textValue != null) {
                                XSSFRichTextString richString = new XSSFRichTextString(textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                }
            }
            workbook = getExportedFile(null, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    public Method getMethod(Field[] fields, int i, T t) {
        Field field = fields[i];
        String fieldName = field.getName();
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Class tCls = t.getClass();
        try {
            return tCls.getMethod(getMethodName, new Class[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
