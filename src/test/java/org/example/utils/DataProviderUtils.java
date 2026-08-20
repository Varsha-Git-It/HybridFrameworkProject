package org.example.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderUtils {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        String path = "./testData/testdatahybrid.xlsx";
        ExcelUtils excel = new ExcelUtils(path, "Sheet1");

        int rowCount = excel.getRowCount();
        int colCount = 3; //excel.getColCount();

        Object[][] data = new Object[rowCount - 1][colCount];   // -1 to skip header row

        for (int i = 1; i < rowCount; i++) {                     // start at 1, skip header
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j).trim();
            }
        }
        System.out.println("Row count detected: " + excel.getRowCount());

        excel.close();
        return data;
    }
}
