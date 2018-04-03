package com.cc.api.automation.utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class GlobalDataProvider {

	protected Configurations config = new Configurations();
	protected WebDriver driver = null;
	protected String BrowserName = null;
	protected String DataQuery = null;
	protected String DataTable = null;
	protected ITestContext currentTest = null;
	protected int NumberOfTestInvocation;
	protected ExtentReports extent;
    static ExtentTest test;
	
public GlobalDataProvider() {

	}

	protected WebDriver getDriver() {
		return this.driver;
	}

	@DataProvider(name = "TestDataProvider")
	public Object[][] globalDataProvider(Method testMethod, ITestContext context) {

		Object[][] testData = null;
		DbConnection dbc = new DbConnection(DbType.TestDatabase);
		System.out.println("Name:" + testMethod.getName().toString());
		String sqlQuery = context.getCurrentXmlTest().getParameter(testMethod.getName().toString());
		System.out.println(testMethod.getName().toString());
		System.out.println("sql query is " + sqlQuery);
		this.currentTest = context;
		testData = dbc.getTestInputList(DataTable, sqlQuery);
		return testData;
	}

	public Map<String, String> getXpaths(String sqlQuery, String pageName) {
		Map<String, String> testData = null;
		DbConnection dbc = new DbConnection(DbType.TestDatabase);
		System.out.println(sqlQuery);
		System.out.println("Page Name=" + pageName);
		testData = dbc.getXpaths(pageName, sqlQuery);
		return testData;
	}

	public int GetNumberOfIteration(String PropertyNameForSQLQuery) {
		String sqlQuery = currentTest.getCurrentXmlTest().getParameter(PropertyNameForSQLQuery);
		DbConnection dbc = new DbConnection(DbType.TestDatabase);
		int rowCount = 0;
		try {
			rowCount = dbc.getRowCount("Test database", sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public static String readCellValue(String Filepath, String Sheet, String CellName) {
		String cellValue = null;
		int intCellValue;
		try {
			Workbook workbook = WorkbookFactory.create(new FileInputStream(Filepath));
			Sheet sheet = workbook.getSheet(Sheet);

			CellReference cr = new CellReference(CellName);
			Row row = sheet.getRow(cr.getRow());
			Cell cell = row.getCell(cr.getCol());
			if (cell.getCellType() == 0) {
				intCellValue = (int) cell.getNumericCellValue();
				cellValue = Integer.toString(intCellValue);
			} else if (cell.getCellType() == 1)
				cellValue = cell.getStringCellValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}


	
	
}
