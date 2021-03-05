package com.hrms.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    private static FileInputStream fileInputStream;
    private static Workbook workbook;
    private static Sheet sheet;

    /*
     * */
    private static void openExcel(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * */
    private static void getSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    /*
     * */
    private static int getNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    /*
     * */
    private static int getNumberOfCells(int rowIndex) {
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    /*
    * */
    private static String getCellData(int rowIndex, int cellIndex) {
        return sheet.getRow(rowIndex).getCell(cellIndex).toString();
    }

    /*
     * */
    public static List<Map<String,String>> excelReaderToListMap(String filePath, String sheetName) {
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> excelList = new ArrayList<>();

        for (int row = 1; row < getNumberOfRows(); row++) {
            Map<String, String> excelMap = new LinkedHashMap<>();
            for (int column = 0; column < getNumberOfCells(row); column++) {
                excelMap.put(getCellData(0, column), getCellData(row, column));
            }
            excelList.add(excelMap);
        }
        return excelList;
    }
}
