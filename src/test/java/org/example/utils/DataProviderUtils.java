package org.example.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "invalid_creds")
    public Object[][] invalidLoginData() throws IOException {
        return filterByResult("Invalid");
    }

    @DataProvider(name = "valid_creds")
    public Object[][] validLoginData() throws IOException {
        return filterByResult("valid");
    }

    private Object[][] filterByResult(String expectedResult) throws IOException {
        String path = "./testData/testdatahybrid.xlsx";
        ExcelUtils excel = new ExcelUtils(path, "Sheet1");

        int rowCount = excel.getRowCount();
        List<Object[]> matchedRows = new ArrayList<>();

        for (int i = 1; i < rowCount; i++) {   // start at 1, skip header
            String username = excel.getCellData(i, 0).trim();
            String password = excel.getCellData(i, 1).trim();
            String result = excel.getCellData(i, 2).trim();

            if (result.equalsIgnoreCase(expectedResult)) {
                matchedRows.add(new Object[]{username, password});
            }
        }

        excel.close();
        return matchedRows.toArray(new Object[0][]);
    }
}
