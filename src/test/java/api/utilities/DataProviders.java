package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	private static ExcelUtility excelUtil;
	private static String path = ".\\TestData\\data driven test.xlsx";
	private static final String SHEET_NAME = "Sheet1";
	
    @DataProvider(name = "myDDTData")
    public Object[][] getAllData() throws IOException  {
        excelUtil = new ExcelUtility(path);

        try {
            int rowCount = excelUtil.getRowCount(SHEET_NAME);
            if (rowCount <=1) {
                throw new IllegalArgumentException(SHEET_NAME+ " Sheet has no data rows.");
            }
            int colCount = excelUtil.getCellCount(SHEET_NAME, 1); // Assuming row 1 contains data
            Object data[][] = new Object[rowCount-1][colCount]; //Exclude header row

            // Populate data from Excel
            for (int i =1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i-1][j] = excelUtil.getCellData(SHEET_NAME, i, j);
                }
            }
            return data;
        } finally {
            excelUtil.close();
        }
    }

    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {
        excelUtil = new ExcelUtility(path);

        try {
            int rowCount = excelUtil.getRowCount(SHEET_NAME);
            if (rowCount <=1) {
                throw new IllegalArgumentException(SHEET_NAME + " Sheet1 has no data rows.");
            }
            Object[] data = new Object[rowCount-1];  //Exclude header row

            // Populate data for userName column
            for (int i =1; i < rowCount; i++) {
            	data[i-1] = excelUtil.getCellData(SHEET_NAME, i, 1); // Assuming column 1 contains usernames
            }
            return data;
        } finally {
            excelUtil.close();
        }
    }
}
