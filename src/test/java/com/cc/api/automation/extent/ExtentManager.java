package com.cc.api.automation.extent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentManager {

	private static String fileName = null;
	/*
	 * private static String filePath = null; private String message = null;
	 * *****
	 */

	private static ExtentReports extent;
	private static ExtentTest test;
	//private static Configurations config = new Configurations();

	// static ExtentTest logger;

	static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-MMM-yyyy hh-mm-ss");
	static String reportPat = System.getProperty("user.dir")
			+ "/test-output/Reports";
	//static String snapShotPath = reportPat + "/Automation-Report";
	static String snapShotPath = reportPat ;
	static String testdatafilepath = "src\\test\\resources\\ConfigSheet.xlsx";
	static String configSheet = "Configuration";

	public static synchronized ExtentReports Instance() {

		if (extent == null) {
			String extentReportPath = snapShotPath + "/Automation-Report "
					 + ".html";
			System.out.println(extentReportPath);
			extent = new ExtentReports(extentReportPath, true,
					DisplayOrder.OLDEST_FIRST);
			ExtentReportUtilites ext = new ExtentReportUtilites();

			InetAddress hostip = ext.hostIpAdrees();
			String hostipadress = hostip.toString();
			String username = ext.userName();
		/*	String Environment = ExcelUtil.readCellValue(testdatafilepath,
					configSheet, "B4");
			Environment = Environment.toLowerCase();
			extent.addSystemInfo("Host Name & I/P Adsress", hostipadress)

					.addSystemInfo("Environment", Environment)
					.addSystemInfo("Browser",
							config.getProperty("BrowserToRun"));*/

			/*For Windows*/
			//	extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			/*For Linux**/
						extent.loadConfig(new File("/var/lib/jenkins/jobs/Genesis_API_Automation/workspace/extent-config.xml"));
		}
		System.out.println("extent object value=" + extent);
		return extent;
	}

	public static ExtentTest loggerInstance(ExtentReports report,
			String suitName) {

		test = report.startTest(suitName);
		return test;

	}

	/*
	 * 
	 * public static String CaptureScreen(WebDriver driver, String ImagesPath) {
	 * TakesScreenshot oScn = (TakesScreenshot) driver; File oScnShot =
	 * oScn.getScreenshotAs(OutputType.FILE); File oDest = new
	 * File(ImagesPath+".jpg"); try { FileUtils.copyFile(oScnShot, oDest); }
	 * catch (IOException e) {System.out.println(e.getMessage());} return
	 * ImagesPath+".jpg"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public static String capture(WebDriver driver,String screenShotName)
	 * throws IOException { TakesScreenshot ts = (TakesScreenshot)driver; File
	 * source = ts.getScreenshotAs(OutputType.FILE); String dest =
	 * "\\ErrorScreenshotsFinal\\"+screenShotName+".png";
	 * System.out.println("Relative Path of Destination="+dest); File
	 * destination = new File(dest); FileUtils.copyFile(source, destination);
	 * 
	 * return dest; }
	 */
	public static String CaprtureScreenshot(WebDriver driver, String message) {

		String fileExtension = ".jpg";
		fileName = dateFormat.format(new Date()).toString() + fileExtension;
		fileName = fileName.replace(" ", "-");

		/*
		 * Configurations config = new Configurations(); filePath =
		 * config.getProperty("ScreenshotsPath");
		 */
		/* SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yy"); */
		// File ImageFolder = new File(Path);
		/* new File(Path+"\\Image").mkdir(); */
		// this.message = message;
		/*
		 * if (snapshotFolder.exists()) {
		 * 
		 * } else { snapshotFolder.mkdir(); }
		 */
		try {
			Thread.sleep(1000);
			try {
				Robot robot = new Robot();
				BufferedImage image = robot.createScreenCapture(new Rectangle(
						Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(image, "JPG", new File(snapShotPath + "/"
						+ fileName));
			} catch (AWTException e) {
				e.printStackTrace();
			}
			Reporter.log("<a href=" + "../test-output/" + snapShotPath + "/"
					+ fileName + " target='_blank' >" + message + "</a>");
			Reporter.log("<br>" + "</br>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// return this;
		System.out.println(snapShotPath + "\\" + fileName);
		// return (snapShotPath +"\\"+ fileName);
		// return ( fileName);
		writeDataToImage(snapShotPath + "\\" + fileName);
		return ("data:image/jpg;base64, " + encodeFileToBase64Binary(new File(
				snapShotPath + "\\" + fileName)));
		// return ( fileName);
	}

	// code added to display USID and machine name on screenshot
	public static void writeDataToImage(String filePath) {
		ExtentReportUtilites ext = new ExtentReportUtilites();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a dd-MMM-yyyy");
		try {
			BufferedImage img = ImageIO.read(new File(filePath));
			img = ImageIO.read(new File(filePath));
			BufferedImage newImage = new BufferedImage(img.getWidth(),
					img.getHeight() + 50, BufferedImage.TYPE_INT_RGB);
			Graphics g2 = newImage.getGraphics();
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
			g2.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);

			// draw graphics
			Color myColour = new Color(0f, 0f, 0f);
			g2.setColor(myColour);
			g2.setFont(new Font("Arial", Font.BOLD, 22));
			g2.drawString(
					"UserName : " + ext.userName() + " HostName : "
							+ (ext.hostIpAdrees()).toString() + " Date : "
							+ sdf.format(new Date()), 20, img.getHeight() + 30);
			g2.dispose();
			File file = new File(filePath);
			ImageIO.write(newImage, "jpg", file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedfile;
	}

}
