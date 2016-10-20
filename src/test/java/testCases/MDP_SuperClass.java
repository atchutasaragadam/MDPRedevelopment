package testCases;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.TestException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.MDP_Constant;
import utility.MDP_Excel;
import utility.MDP_ExtentManager;
import utility.MDP_MyCustomisedLogs;

public class MDP_SuperClass {

	public static WebDriver driver;

	public static ExtentTest test;
	public static ExtentReports extent;
	public static DesiredCapabilities caps;

	@AfterTest
	public static void afterTest() throws Exception {

		extent.endTest(test);
		extent.flush();
		extent.close();
		Thread.sleep(10000);
		driver.quit();

	}

	@BeforeTest(alwaysRun = true)
	@Parameters("browser")
	public static void browserExecute(String browser) throws Exception {
		try {

			// configuring logs
			DOMConfigurator.configure("log4j.xml");

			extent = MDP_ExtentManager.Instance();

			MDP_MyCustomisedLogs.info("Launching Browser < " + browser + " >");
			test = extent.startTest("Launching Browser < " + browser + " >");

			// executes based on the browser parameter
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Browsers/FirefoxDriver/geckodriver");

				caps = DesiredCapabilities.firefox();
				// caps.setBrowserName("firefox");
				caps.setCapability("marionette", true);
				caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				// caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				// caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				// true);
				// caps.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				// caps.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,
				// true);
				// driver = new MarionetteDriver();

				// driver=new RemoteWebDriver(new
				// URL(MDP_Constant.str_NodeURL),caps);
				driver = new FirefoxDriver(caps);

				MDP_MyCustomisedLogs.info("New firefox driver instantiated");
				test.log(LogStatus.INFO, "New firefox driver instantiated");

			} else if (browser.equalsIgnoreCase("chrome")) {

				// System.setProperty("webdriver.chrome.driver",
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Browsers/ChromeDriver/chromedriver");
				caps = DesiredCapabilities.chrome();

				// ChromeOptions options = new ChromeOptions();
				// options.CAPABILITY
				// options.setBinary(new File("/path/to/chrome"));
				// caps.setCapability(ChromeOptions.CAPABILITY, true);
				driver = new ChromeDriver(caps);
				// driver = new ChromeDriver();

				MDP_MyCustomisedLogs.info("New Chrome driver instantiated");
				test.log(LogStatus.INFO, "New Chrome driver instantiated");

			} else if (browser.equalsIgnoreCase("safari")) {
				SafariOptions options = new SafariOptions();
				options.setUseCleanSession(true);
				options.wait(3000);
				
				caps = DesiredCapabilities.safari();
				caps.setCapability(SafariOptions.CAPABILITY, options);

				driver = new SafariDriver(caps);

				MDP_MyCustomisedLogs.info("New Safari driver instantiated");
				test.log(LogStatus.INFO, "New Safari driver instantiated");

			} else if (browser.equalsIgnoreCase("browser-stack-Win-Edge-13")) {

				caps = new DesiredCapabilities();
				caps.setCapability("browser", "MicrosoftEdge");
				caps.setCapability("borwser_version", "13");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_Version", "10");
				caps.setCapability("browserstack.debug", "true");

				driver = new RemoteWebDriver(new URL(MDP_Constant.BS_URL), caps);

				MDP_MyCustomisedLogs.info("New Windows Edge 13  driver instantiated via browser stack");
				test.log(LogStatus.INFO, "New Windows Edge 13  driver instantiated via browser stack");

			} else if (browser.equalsIgnoreCase("browser-stack-Android-Samsung-S5 Mini")) {

				caps = new DesiredCapabilities();
				caps.setCapability("browserName", "android");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("device", "Samsung Galaxy S5");
				caps.setCapability("browserstack.debug", "true");

				driver = new RemoteWebDriver(new URL(MDP_Constant.BS_URL), caps);

				MDP_MyCustomisedLogs.info("New Android- Samsung Galaxy S5  driver instantiated via browser stack");
				test.log(LogStatus.INFO, "New Android- Samsung Galaxy S5  driver instantiated via browser stack");

			} else if (browser.equalsIgnoreCase("selenium-grid-win10-IE11")) {

				caps = DesiredCapabilities.internetExplorer();
				caps.setBrowserName("internet explorer");
				caps.setPlatform(Platform.WINDOWS);

				driver = new RemoteWebDriver(new URL(MDP_Constant.selenium_grid_Localhost), caps);

				MDP_MyCustomisedLogs.info("Win 10 - IE11  driver instantiated via Selenium Grid");
				test.log(LogStatus.INFO, "Win 10 - IE11  driver instantiated via Selenium Grid");

			}

			else if (browser.equalsIgnoreCase("selenium-grid-win10-MSEdge")) {

				caps = DesiredCapabilities.edge();

				caps.setBrowserName("MicrosoftEdge");
				caps.setPlatform(Platform.WIN10);

				driver = new RemoteWebDriver(new URL(MDP_Constant.selenium_grid_Localhost), caps);

				MDP_MyCustomisedLogs.info("Win 10 - MS Edge  driver instantiated via Selenium Grid");
				test.log(LogStatus.INFO, "Win 10 - MS Edge  driver instantiated via Selenium Grid");

			}
		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			//throw new Exception(e.toString());

		}
	}

	public static void getScreenshot(WebDriver driver, String sTestCaseID, String sResult, String sLinkText)
			throws Exception {

		try {

			DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy_hh:mm");
			Date date = new Date();
			String sdate = dateFormat.format(date);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(MDP_Constant.ScreenshotPath + sTestCaseID + "_" + sLinkText + "_"
					+ sResult + "_" + sdate + ".png"));

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error("Class : SuperClass || Method: getScreenshot || Desciption: " + e.toString());
			test.log(LogStatus.ERROR, "Class : SuperClass || Method: getScreenshot || Desciption: " + e.toString());
		throw new TestException("Class : SuperClass || Method: getScreenshot || Desciption: " + e.toString());

		}

	}

	public static void getURLFromSpreadsheet() throws Exception {
		try {

			int i_RowCount = MDP_Excel.getRowCount(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestCases);
			for (int i = 1; i <= i_RowCount; i++) {

				String strTestCaseID = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestCases,
						i, MDP_Constant.Col_TestCaseID);
				String strRunMode = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestCases, i,
						MDP_Constant.Col_RunMode);
				if (strRunMode.equalsIgnoreCase("y") || strRunMode.equalsIgnoreCase("yes")) {

					MDP_MyCustomisedLogs.startTestCase(strTestCaseID);
					test.log(LogStatus.INFO, "Starting Test case: <  " + strTestCaseID + " >");

					String strURL = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestCases, i,
							MDP_Constant.Col_URL);

					driver.manage().window().maximize();
					driver.get(strURL);

					MDP_MyCustomisedLogs.info("navigated to " + strURL);
					test.log(LogStatus.INFO, "navigated to " + strURL);

					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				} else {

					MDP_MyCustomisedLogs.skipTestCase(strTestCaseID, "Skipping the test case < " + strTestCaseID
							+ " > as the Run Mode for this test case is < " + strRunMode + " >");
					test.log(LogStatus.SKIP, "Skipping the test case < " + strTestCaseID
							+ " > as the Run Mode for this test case is < " + strRunMode + " >");

				}

			}
		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

		throw new TestException(e.toString());
		}
	}

	public static void getURLKoala() throws Exception {
		driver.get(MDP_Constant.str_KoalaURL);

	}

}
