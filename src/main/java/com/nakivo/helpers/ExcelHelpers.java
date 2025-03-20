package com.nakivo.helpers;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ExcelHelpers {
    private FileInputStream fis;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public ExcelHelpers() {
    }

    public void setExcelFile(String excelPath, String sheetName) {
        log.info("Set Excel File: " + excelPath);
        log.info("Sheet Name: " + sheetName);

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    log.info("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    log.info("The Sheet Name is empty.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                try {
                    log.info("Sheet name not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            excelFilePath = excelPath;

            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String getCellData(int rowNum, int colNum) {
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING -> CellData = cell.getStringCellValue();
                case NUMERIC -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                }
                case BOOLEAN -> CellData = Boolean.toString(cell.getBooleanCellValue());
                case BLANK -> CellData = "";
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(int rowNum, String columnName) {
        return getCellData(rowNum, columns.get(columnName));
    }
}
