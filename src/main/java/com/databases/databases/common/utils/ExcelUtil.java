package com.databases.databases.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;


public final class ExcelUtil {

    // 获取操作文件对象
    public static Workbook getWorkbook(File file) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    // 获取所有表格对象
    public static LinkedList<Sheet> getSheetAll(Workbook workbook) {
        LinkedList<Sheet> sheets = new LinkedList<Sheet>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i ++) {
            sheets.add(workbook.getSheetAt(i));
        }
        return sheets;
    }

    // 获取指定表格对象，起始下标为0
    public static Sheet getSheet(Workbook workbook, int index) {
        LinkedList<Sheet> sheets = getSheetAll(workbook);
        if (index < sheets.size()) {
            return sheets.get(index);
        }
        return null;
    }

    // 获取指定单元格对象，起始坐标左上角0，0
    public static Cell getCell(Sheet sheet, int x, int y) {
        Row row = sheet.getRow(y);
        if (row == null) {
            return null;
        }
        return row.getCell(x);
    }

    public static Cell getCell(Sheet sheet, String position) {
        return getCell(sheet, getPosition(position)[0], getPosition(position)[1]);
    }

    // 坐标转换
    public static int[] getPosition(String position) {
        char[] headerPosition = position.toCharArray();
        int headerX = 0;
        int headerY = 0;
        for (int i = 0; i < headerPosition.length; i ++) {
            if ('A' <= headerPosition[i] && 'Z' >= headerPosition[i]) {
                headerX = headerX * 26  + ((int) headerPosition[i] - 64);
            } else if ('a' <= headerPosition[i] && 'z' >= headerPosition[i]) {
                headerX = headerX * 26  + ((int) headerPosition[i] - 96);
            } else if ('0' <= headerPosition[i] && '9' >= headerPosition[i]) {
                headerY = headerY * 10 + (headerPosition[i] - '0');
            }
        }

        return new int[]{headerX - 1, headerY - 1};
    }

    public static String getPosition(Integer[] position) {
        int headerX = position[0];
        int headerY = position[1] + 1;
        String finalPositionX = "";
        String finalPositionY = "";

        while (true) {
            int result = headerY % 10;
            finalPositionY = result + finalPositionY;
            headerY = headerY / 10;
            if (headerY == 0) {
                break;
            }
        }

        do {
            if (finalPositionX.length() > 0) {
                headerX--;
            }
            finalPositionX = ((char) (headerX % 26 + (int) 'A')) + finalPositionX;
            headerX = (int) ((headerX - headerX % 26) / 26);
        } while (headerX > 0);

        return finalPositionX + finalPositionY;
    }

    // 获取单元格数据
    public static String getCellData(Cell cell) {
        String cellData = "";

        try {
            switch (cell.getCellType()) {
                case NUMERIC: //数字
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        cellData = sdf.format(date);
                    } else {
                        cellData = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case STRING: //字符串
                    cellData = String.valueOf(cell.getStringCellValue());
                    break;
                case BOOLEAN: //Boolean
                    cellData = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA: //公式
                    cellData = String.valueOf(cell.getStringCellValue());
//                    cellData = String.valueOf(cell.getCellFormula());
                    break;
                case BLANK: //空值
                    cellData = "";
                    break;
                case ERROR: //故障
                    cellData = "ERROR";
                    break;
                default:
                    cellData = "UNKNOWN";
                    break;
            }
        } catch (Exception e) {
            return "ERROR";
        }

        return cellData;
    }

    public static String getSheetHeaderEnd(Sheet sheet, String beginPosition, boolean readDirection) {
        int[] beginPositionNumber = getPosition(beginPosition);
        int beginPositionX = beginPositionNumber[0];
        int beginPositionY = beginPositionNumber[1];

        if (readDirection) {
            int i = 0;
            while (true) {
                Cell cell = getCell(sheet, beginPositionX + i, beginPositionY);
                if (cell == null) {
                    return getPosition(new Integer[]{beginPositionX + i, beginPositionY});
                }
                String cellData = getCellData(cell);
                if (cellData == null || StringUtils.isEmpty(cellData)
                        || "ERROR".equals(cellData) || "ERROR".equals(cellData)) {
                    return getPosition(new Integer[]{beginPositionX + i, beginPositionY});
                }

                i ++;
            }
        } else {
            int i = 0;
            while (true) {
                Cell cell = getCell(sheet, beginPositionX, beginPositionY + i);
                if (cell == null) {
                    return getPosition(new Integer[]{beginPositionX, beginPositionY + i});
                }
                String cellData = getCellData(cell);
                if (cellData == null || StringUtils.isEmpty(cellData)
                        || "ERROR".equals(cellData) || "ERROR".equals(cellData)) {
                    return getPosition(new Integer[]{beginPositionX, beginPositionY + i});
                }

                i ++;
            }
        }
    }

    // 获取表头内容
    public static String[] getSheetHeader(Sheet sheet, String beginPosition, String endPosition) {
        int[] beginPositionNumber = getPosition(beginPosition);
        int[] endPositionNumber = getPosition(endPosition);

        if (beginPositionNumber[0] == endPositionNumber[0]) {
            int headerLength = endPositionNumber[1] - beginPositionNumber[1] + 1;
            String[] resultHeader = new String[headerLength];
            for (int i = 0; i < headerLength; i ++) {
                if (isMergedCell(sheet, getPosition(new Integer[]{beginPositionNumber[0], beginPositionNumber[1] + i}))) {
                    resultHeader[i] = getMergedCellData(sheet, getPosition(new Integer[]{beginPositionNumber[0], beginPositionNumber[1] + i}));
                } else {
                    Cell cell = getCell(sheet, beginPositionNumber[0], beginPositionNumber[1] + i);
                    resultHeader[i] = getCellData(cell);
                }
            }

            return resultHeader;
        }

        if (beginPositionNumber[1] == endPositionNumber[1]) {
            int headerLength = endPositionNumber[0] - beginPositionNumber[0] + 1;
            String[] resultHeader = new String[headerLength];
            for (int i = 0; i < headerLength; i ++) {
                if (isMergedCell(sheet, getPosition(new Integer[]{beginPositionNumber[0] + i, beginPositionNumber[1]}))) {
                    resultHeader[i] = getMergedCellData(sheet, getPosition(new Integer[]{beginPositionNumber[0] + i, beginPositionNumber[1]}));
                } else {
                    Cell cell = getCell(sheet, beginPositionNumber[0] + i, beginPositionNumber[1]);
                    resultHeader[i] = getCellData(cell);
                }
            }

            return resultHeader;
        }

        return new String[]{};
    }

    // 判断是否是合并单元格
    public static boolean isMergedCell(Sheet sheet , String position) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        int[] positionNumber = getPosition(position);
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(positionNumber[1] >= firstRow && positionNumber[1] <= lastRow){
                if(positionNumber[0] >= firstColumn && positionNumber[0] <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    // 获取合并单元格数据
    public static String getMergedCellData(Sheet sheet, String position){
        int sheetMergeCount = sheet.getNumMergedRegions();
        int[] positionNumber = getPosition(position);
        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(positionNumber[1] >= firstRow && positionNumber[1] <= lastRow){
                if(positionNumber[0] >= firstColumn && positionNumber[0] <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellData(fCell) ;
                }
            }
        }
        return null ;
    }

    public static LinkedList<String[]> getSheetData(Sheet sheet, String beginPosition, String endPosition, boolean readDirection) {
        int[] beginPositionNumber = getPosition(beginPosition);
        int[] endPositionNumber = getPosition(endPosition);

        LinkedList<String[]> resultData = new LinkedList<String[]>();

        if (readDirection) {
            int rowNumber = endPositionNumber[1] - beginPositionNumber[1] + 1;
            for (int i = 0; i < rowNumber; i ++) {
                String rowBeginPosition = getPosition(new Integer[]{beginPositionNumber[0], beginPositionNumber[1] + i});
                String rowEndPosition = getPosition(new Integer[]{endPositionNumber[0], beginPositionNumber[1] + i});
                String[] resultRow = getSheetHeader(sheet, rowBeginPosition, rowEndPosition);
                resultData.add(resultRow);
            }
        } else {
            int colNumber = endPositionNumber[0] - beginPositionNumber[0] + 1;
            for (int i = 0; i < colNumber; i ++) {
                String colBeginPosition = getPosition(new Integer[]{beginPositionNumber[0] + i, beginPositionNumber[1]});
                String colEndPosition = getPosition(new Integer[]{beginPositionNumber[0] + i, endPositionNumber[1]});
                String[] resultCol = getSheetHeader(sheet, colBeginPosition, colEndPosition);
                resultData.add(resultCol);
            }
        }

        return resultData;
    }

    public static void main(String args[]) {
        Workbook workbook = ExcelUtil.getWorkbook(new File("/home/wu_chenyang/333.xls"));
        Sheet sheet = getSheet(workbook, 0);
//        LinkedList<String[]> sheetData = getSheetData(sheet, "A6", "AM15", true);
//        for (String[] data : sheetData) {
//            String result = "";
//            for (String temp : data) {
//                if (StringUtils.isEmpty(result)) {
//                    result = temp;
//                } else {
//                    result = result + "\t" + temp;
//                }
//            }
//            System.out.println(result);
//        }
//        Cell m2 = getCell(sheet, "D734");
//        String cellData = getCellData(m2);
//        System.out.println(cellData.split("\n").length);
    }
}
