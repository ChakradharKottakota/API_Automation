package com.cc.api.automation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;



public class DbConnection extends GlobalDataProvider {

	private Connection conn = null;
	private java.sql.Statement statement = null;
	private ResultSet resultSet;
	private String dbType = null;
	String dbUrl = "DBURL";
	String userName = "USERNAME";
	String passWord = "PASSWORD";
	Connection dbConn;
	Statement dbStmt;
	Properties props = null;
	String testDataFilePath = "src\\test\\resources\\Config.xlsx";
	String configSheet = "Config";

	/**
	 * Constroctor
	 * 
	 * @param databaseType
	 */
	public DbConnection(String databaseType) {
		new Configurations();
		dbType = databaseType;
		dbUrl = config.getProperty("DBURL");
		userName = config.getProperty("USERNAME");
		passWord = config.getProperty("PASSWORD");
	}

	/**
	 * executes and ready with the db connection before the Test starts
	 * 
	 */
	@BeforeClass
	public void setUpBeforeClass() {
		props = new Properties();
		dbConn = getConnection(dbUrl,userName,passWord);
	}

	/**
	 * To get the connection using the url username and password
	 * 
	 * @param dbURL
	 * @param username
	 * @param password
	 * @return
	 */
	public Connection getConnection(String dbURL,String username,String password) {

		if (dbType.equals("Testing")) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				if (null != dbURL && null != username && null != password)
					conn = DriverManager.getConnection(dbURL, username, password);
			}
			catch (Exception e) {
				System.out.println("Could not get connection -- " + e.toString());
			}
		}
		return conn;
	}

	/**
	 * Gets the test Inupts
	 * 
	 * @param sqlQuery
	 * @return
	 */

	public ResultSet getTestInput(String sqlQuery) {
		if (conn == null) {
			getConnection(dbUrl, userName, passWord);
		}
		try {
			statement = conn.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE,
					resultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
		}
		return resultSet;
	}

	/**
	 * gets the data to given values
	 * 
	 * @param tableName
	 * @param sqlQuery
	 * @return
	 */

	public ResultSet getTestInput(String tableName, String sqlQuery) {
		if (conn == null) {
			getConnection(dbUrl, userName, passWord);
		}
		try {
			statement = conn.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE,
					resultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("Could not execute query " + e.toString());
		}
		return resultSet;
	}

	/**
	 * validates the query
	 * 
	 * @param sqlQuery
	 * @param driver
	 * @return
	 */

	public ResultSet validateQueryAndReturnResults(String sqlQuery, WebDriver driver) {
		boolean queryValidateSuccess = false;
		try {
			if (conn == null || conn.isClosed()) {
				try {
					getConnection(dbUrl, userName, passWord);
				}

				catch (Exception e) {
					Assert.assertNotNull(
							conn,
							"Failed to get connection to development database with exception "
									+ e.toString());
					driver.close();
					driver.quit();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {

			statement = conn.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE,
					resultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlQuery);
			queryValidateSuccess = true;
		}

		catch (SQLException e) {
			
			
			Assert.assertTrue(queryValidateSuccess,
					"Query validation failed with exception " + e.toString());
			CloseConnection();
		}
		return resultSet;
	}

	/**
	 * @param sqlQuery
	 * @param driver
	 */
	public void UpdateRecords(String sqlQuery, WebDriver driver) {
		boolean QueryValidateSuccess = false;
		try {
			if (conn == null || conn.isClosed()) {
				try {
					getConnection(dbUrl, userName, passWord);
				} catch (Exception e) {
					Assert.assertNotNull(
							conn,
							"Failed to get connection to development database with exception "
									+ e.toString());
					driver.close();
					driver.quit();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement = conn.createStatement();
			statement.executeUpdate(sqlQuery);
			QueryValidateSuccess = true;
			System.out.println("Query Update Sucessful...");
		}

		catch (SQLException e) {
			
			Assert.assertTrue(QueryValidateSuccess,
					"Query validation failed with exception " + e.toString());
			CloseConnection();
		}
		CloseConnection();
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public int getRowCount(String tableName) throws SQLException {
		ResultSet resultSet = getTestInput(tableName);
		resultSet.last();
		return resultSet.getRow();
	}

	/**
	 * 
	 * @param resultSet
	 * @return
	 */

	public int getRecordsCount(ResultSet resultSet) {
		int count = 0;
		try {
			resultSet.last();
			count = resultSet.getRow();
			resultSet.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 
	 * @param tableName
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 */

	public int getRowCount(String tableName, String sqlQuery) throws SQLException {
		ResultSet resultSet = getTestInput(tableName, sqlQuery);
		resultSet.last();
		return resultSet.getRow();
	}

	/**
	 * 
	 * @param dataTableName
	 * @param sqlQuery
	 * @return
	 */
	public Object[][] getTestInputList(String dataTableName, String sqlQuery) {
		Object[][] testInputList = null;

		ResultSet resultSet = getTestInput(dataTableName, sqlQuery);
		Map<String, String> dataMap = null;
		try {
			testInputList = new Object[1][];
			testInputList[0] = new Object[1];
			dataMap = new HashMap<String, String>();
			if (resultSet != null) {
				while (resultSet.next()) {
					String key = resultSet.getString("keyname");
					String value = resultSet.getString("value");
					dataMap.put(key, value);
				}
			}
			testInputList[0][0] = dataMap;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testInputList;

	}

	/**
	 * close connection
	 */
	public void CloseConnection() {
		try {
			this.conn.close();
		} catch (Exception e) {

		}
	}

	/**
	 * gets the details and creates the Map object
	 * 
	 */
	public Map<String, String> getXpaths(String pageName, String sqlQuery) {
		Connection con = getConnection(dbUrl, userName, passWord);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Map<String, String> dataMap = new HashMap<String, String>();
		try {

			pstmt = con.prepareStatement(sqlQuery, resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, pageName);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
				System.out.println(resultSet.getString(2));
				dataMap.put(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
}
