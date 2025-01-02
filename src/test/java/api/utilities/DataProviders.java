package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "myDDTData")
    public String[][] getAllData() throws IOException  {
        String path = ".\\TestData\\data driven test.xlsx";
        ExcelUtility excelUtil = new ExcelUtility(path);

        try {
            int rowCount = excelUtil.getRowCount("Sheet1");
            if (rowCount <=1) {
                throw new IllegalArgumentException("Sheet1 has no data rows.");
            }
            int colCount = excelUtil.getCellCount("Sheet1", 1); // Assuming row 1 contains data
            String apiData[][] = new String[rowCount-1][colCount]; //Exclude header row

            // Populate data from Excel
            for (int i =1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    apiData[i-1][j] = excelUtil.getCellData("Sheet1", i, j);
                }
            }
            return apiData;
        } finally {
            excelUtil.close();
        }
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = ".\\TestData\\data driven test.xlsx";
        ExcelUtility excelUtil = new ExcelUtility(path);

        try {
            int rowCount = excelUtil.getRowCount("Sheet1");
            if (rowCount <=1) {
                throw new IllegalArgumentException("Sheet1 has no data rows.");
            }
            String[] apidata = new String[rowCount-1];  //Exclude header row

            // Populate data for userName column
            for (int i =1; i < rowCount; i++) {
            	apidata[i-1] = excelUtil.getCellData("Sheet1", i, 1); // Assuming column 1 contains usernames
            }
            return apidata;
        } finally {
            excelUtil.close();
        }
    }
}
