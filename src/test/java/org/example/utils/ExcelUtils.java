package org.example.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String filePath;
    // removed fis as instance variable - not needed after constructor

    // constructor - opens the excel file
    public ExcelUtils(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        fis.close(); // close immediately after workbook is loaded into memory
        sheet = workbook.getSheet(sheetName);
    }

    // get total row count
    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    // get total column count
    public int getColCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // read cell data by row and column index
    public String getCellData(int rowIndex, int colIndex) {
        DataFormatter formatter = new DataFormatter();
        XSSFCell cell = sheet.getRow(rowIndex).getCell(colIndex);
        return formatter.formatCellValue(cell);
    }

    // write data to a cell
    public void setCellData(int rowIndex, int colIndex, String value) throws IOException {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        XSSFCell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        cell.setCellValue(value);

        // save to file - fis already closed so no lock conflict
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }
    public void setCellDataWithColor(int rowIndex, int colIndex, String value, String color) throws IOException {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        XSSFCell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        cell.setCellValue(value);

        // create cell style
        XSSFCellStyle style = workbook.createCellStyle();

        // fill color
        if (color.equalsIgnoreCase("green")) {
            style.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0, (byte) 153, (byte) 0}, null));
        } else if (color.equalsIgnoreCase("red")) {
            style.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 255, (byte) 0, (byte) 0}, null));
        }
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // borders
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // alignment
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        cell.setCellStyle(style);

        // save to file
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    // close workbook only - fis already closed in constructor
    public void close() throws IOException {
        workbook.close();
    }
}
