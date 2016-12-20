package au.com.mazda.MDP.TestCases;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

//import com.thoughtworks.selenium.Wait.WaitTimedOutException;

import au.com.mazda.MDP.Utility.MDP_Constant;
import au.com.mazda.MDP.Utility.MDP_Excel;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class SuperClass {

	public static WebDriver driver;

	public static String str_DriverBrowserName;
	public static String str_DriverOS;
	public static String str_DriverVersion;
	public static String strURL;
	public static DesiredCapabilities caps, capsDevices;

	public static String strTestName;
	
	

	// @AfterTest
	@AfterClass(alwaysRun = true)
	public static void afterTest() {
		try {

			waitImplictly(driver);
			driver.quit();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

	@BeforeMethod(alwaysRun = true)
	protected void startMethod(Method method) {
		try {
			strTestName = method.getName();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

	@AfterMethod(alwaysRun = true)
	protected void endMethod(Method method) {
		try {
			strTestName = method.getName();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}
	// @BeforeTest(alwaysRun = true)
	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public static void browserExecute(String browser) throws Exception {
		try {

			// configuring logs
			DOMConfigurator.configure("log4j.xml");

			MDP_MyCustomisedLogs.info("Launching Browser < " + browser + " >");

			// executes based on the browser parameter
			if (browser.equalsIgnoreCase("mac - firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")
								+ "/Browsers/geckodriver");

				caps = DesiredCapabilities.firefox();

				caps.setCapability("marionette", true);
				caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);


				driver = new FirefoxDriver(caps);

				MDP_MyCustomisedLogs.info("New firefox driver instantiated" +getBrowserDetails(caps));

			} else if (browser.equalsIgnoreCase("mac - chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "/Browsers/chromedriver");
				caps = DesiredCapabilities.chrome();

				str_DriverBrowserName = caps.getBrowserName().toLowerCase();
				str_DriverOS = caps.getPlatform().toString();
				str_DriverVersion = caps.getVersion().toString();
				driver = new ChromeDriver(caps);

				MDP_MyCustomisedLogs.info("New Chrome driver instantiated" +getBrowserDetails(caps));

			} else if (browser.equalsIgnoreCase("mac - safari")) {
				System.setProperty("webdriver.safari.driver",
						System.getProperty("user.dir")
								+ "/Browsers/safaridriver");
				caps = DesiredCapabilities.safari();
				SafariOptions options = new SafariOptions();
				options.setUseCleanSession(true);
				caps.setCapability(SafariOptions.CAPABILITY, options);

				str_DriverBrowserName = caps.getBrowserName().toLowerCase();
				str_DriverOS = caps.getPlatform().toString();
				str_DriverVersion = caps.getVersion().toString();

				driver = new SafariDriver(caps);
				MDP_MyCustomisedLogs.info("New Safari driver instantiated" +getBrowserDetails(caps));

			}
			if (browser.equalsIgnoreCase("Windows - firefox")) {

				// for Windows
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")
								+ "\\Browsers\\geckodriver_win64.exe");
				caps = DesiredCapabilities.firefox();

				caps.setCapability("marionette", true);
				caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

				driver = new FirefoxDriver(caps);

				MDP_MyCustomisedLogs.info("New firefox driver instantiated" +getBrowserDetails(caps));

			} else if (browser.equalsIgnoreCase("Windows - chrome")) {

				// for Windows
				capsDevices = invokeChromeWindows(driver);
				
				MDP_MyCustomisedLogs.info("New Chrome driver instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase("Windows - IE11")) {

				// String IEPath = System.getProperty("user.dir");
				// String STRIEPath = IEPath + "\\Browsers\\";
				// System.out.println("IEPath: " + IEPath);
				// System.out.println("STRIEPath : " + STRIEPath);
				// Runtime rt = Runtime.getRuntime();
				// rt.exec("cmd.exe /c cd \""+STRIEPath+"\" & start cmd.exe /k
				// \"java -jar selenium-server-standalone-3.0.0-beta2.jar -role
				// webdriver -nodeConfig BrowserCapabilities.json");

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")
								+ "\\Browsers\\IEDriverServer_win32.exe");
				// driver = new EdgeDriver();
				driver = new InternetExplorerDriver();
				MDP_MyCustomisedLogs.info("New IE driver instantiated" +getBrowserDetails(capsDevices));
				// caps =DesiredCapabilities.internetExplorer();
				// caps.setBrowserName("internet explorer");
				//
				// driver = new RemoteWebDriver(new
				// URL(MDP_Constant.str_NodeURL), caps);
			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPhone  5s")) {
				capsDevices = invokeChromeWindows(driver);

				
				capsDevices.setCapability("browserName", "iPhone");
				capsDevices.setCapability("platform", "MAC");
				capsDevices.setCapability("device", "iPhone  5S");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPhone  5s  driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPhone  6  Plus")) {

				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "iPhone");
				capsDevices.setCapability("platform", "MAC");
				capsDevices.setCapability("device", "iPhone  6  Plus");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPhone  6  Plus    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPhone  6s")) {

				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "iPhone");
				capsDevices.setCapability("platform", "MAC");
				capsDevices.setCapability("device", "iPhone  6S");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPhone  6s  driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase(
					"browser-stack-Apple  iPhone  6s  Plus")) {

				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "iPhone");
				capsDevices.setCapability("platform", "MAC");
				capsDevices.setCapability("device", "iPhone  6S  Plus");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPhone  6s  Plus    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPhone  6")) {
				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "iPhone");
				capsDevices.setCapability("platform", "MAC");
				capsDevices.setCapability("device", "iPhone  6");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPhone  6    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Samsung  Galaxy  S5")) {

				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "android");
				capsDevices.setCapability("platform", "ANDROID");
				capsDevices.setCapability("device", "Samsung  Galaxy  S5");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Samsung  Galaxy  S5    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Google  Nexus  5")) {

				capsDevices = invokeChromeWindows(driver);
				capsDevices.setCapability("browserName", "android");
				capsDevices.setCapability("platform", "ANDROID");
				capsDevices.setCapability("device", "Google  Nexus  5");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						capsDevices);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Google  Nexus  5    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase("browser-stack-Samsung  S4")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "android");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("device", "Samsung  Galaxy  S4");

			
				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Samsung  S4    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPad  Mini  4")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "iPad");
				caps.setCapability("platform", "MAC");
				caps.setCapability("device", "iPad  Mini  4");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPad  Mini  4    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPad  Mini")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "iPad");
				caps.setCapability("platform", "MAC");
				caps.setCapability("device", "iPad  Mini");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPad  Mini    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  ipad  Air")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "iPad");
				caps.setCapability("platform", "MAC");
				caps.setCapability("device", "iPad  Air");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  ipad  Air    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Google  Nexus  7")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "android");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("device", "Google  Nexus  7");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Google  Nexus  7    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase(
					"browser-stack-Samsung  Galaxy  Tab  4  10.1")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "android");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("device", "Samsung  Galaxy  Tab  4  10.1");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New browser-stack-Samsung Galaxy Tab  4  10.1 driver instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase(
					"browser-stack-Samsung  Galaxy  Note  10.1")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "android");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("device", "Samsung  Galaxy  Note  10.1");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Samsung  Galaxy  Note  10.1 driver instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPad  Pro")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "iPad");
				caps.setCapability("platform", "MAC");
				caps.setCapability("device", "iPad  Pro");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPad  Pro    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser
					.equalsIgnoreCase("browser-stack-Apple  iPad  Mini  2")) {

				capsDevices = invokeChromeWindows(driver);
				caps.setCapability("browserName", "iPad");
				caps.setCapability("platform", "MAC");
				caps.setCapability("device", "iPad  Mini  2");

				driver = new RemoteWebDriver(new URL(MDP_Constant.S_BS_URL),
						caps);

				MDP_MyCustomisedLogs.info(
						"New  browser-stack-Apple  iPad  Mini  2    driver  instantiated" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase("selenium-grid-win10-IE11")) {

				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "11.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "10");
				caps.setCapability("resolution", "1024x768");

				driver = new RemoteWebDriver(
						new URL(MDP_Constant.S_SELENIUM_GRID_LOCALHOST), caps);

				MDP_MyCustomisedLogs.info(
						"selenium-grid-win10-IE11 driver  instantiated  via  browser  stack" +getBrowserDetails(capsDevices));

			} else if (browser.equalsIgnoreCase("selenium-grid-win10-MSEdge")) {

				caps = DesiredCapabilities.edge();

				caps.setCapability("browser", "Edge");
				caps.setCapability("browser_version", "13.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "10");
				caps.setCapability("resolution", "1024x768");

				driver = new RemoteWebDriver(
						new URL(MDP_Constant.S_SELENIUM_GRID_LOCALHOST), caps);

				MDP_MyCustomisedLogs.info(
						"selenium-grid-win10-MSEdge driver instantiated  via browser stack" +getBrowserDetails(capsDevices));

			}
		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());

		}
	}

public static DesiredCapabilities invokeChromeWindows(WebDriver driver) throws Exception {
	try {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\Browsers\\chromedriver.exe");
		caps = DesiredCapabilities.chrome();

		driver = new ChromeDriver(caps);
		

	} catch(Exception e) {
		MDP_MyCustomisedLogs.error(e.toString());
		throw new Exception(e.toString());
	}
	return caps;

}

public static String getBrowserDetails(DesiredCapabilities caps) throws Exception {
	try {
	
		str_DriverBrowserName = caps.getBrowserName().toLowerCase();
		str_DriverOS = caps.getPlatform().toString();
		str_DriverVersion = caps.getVersion().toString();
		
	} catch (Exception e) {
		MDP_MyCustomisedLogs.error(e.toString());
		throw new Exception(e.toString());
	}
	return str_DriverBrowserName+"_"+str_DriverOS+"_"+str_DriverVersion;
}
	public static void getScreenshot( String sCaps, String sTestCaseID, String sResult,
			String sLinkText) throws Exception {

		try {
			
			DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy_hh:mm");
			Date date = new Date();
			String sdate = dateFormat.format(date);
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(MDP_Constant.S_SCREENSHOT_PATH +"_"+ sCaps+"_" + sTestCaseID + "_"
							+ sLinkText + "_" + sResult + "_" + sdate
							+ ".png"));

		} catch (Exception e) {

			MDP_MyCustomisedLogs
					.error("Class : SuperClass || Method: getScreenshot || Desciption: "
							+ e.toString());
			throw new Exception(e.toString());

		}

	}

	public static void getURLFromSpreadsheet() throws Exception {
		try {
			int i_RowCount = MDP_Excel.getRowCount(
					MDP_Constant.S_EXCEL_FILE_PATH,
					MDP_Constant.S_SHEET_TEST_CASES);
			for (int i = 1; i <= i_RowCount; i++) {

				String strTestCaseID = MDP_Excel.getCellValue(
						MDP_Constant.S_EXCEL_FILE_PATH,
						MDP_Constant.S_SHEET_TEST_CASES, i,
						MDP_Constant.I_COL_TEST_CASE_ID);
				String strRunMode = MDP_Excel.getCellValue(
						MDP_Constant.S_EXCEL_FILE_PATH,
						MDP_Constant.S_SHEET_TEST_CASES, i,
						MDP_Constant.I_COL_RUN_MODE);
				if (strRunMode.equalsIgnoreCase("y")
						|| strRunMode.equalsIgnoreCase("yes")) {

					MDP_MyCustomisedLogs.startTestCase(strTestCaseID);

					strURL = MDP_Excel.getCellValue(
							MDP_Constant.S_EXCEL_FILE_PATH,
							MDP_Constant.S_SHEET_TEST_CASES, i,
							MDP_Constant.I_COL_URL);

					driver.manage().window().maximize();
					waitImplictly(driver);
					driver.get(strURL);
					loginToEpiServer(getBrowserDetails(capsDevices));

					MDP_MyCustomisedLogs.info("navigated to " + strURL);

					waitImplictly(driver);

				} else if (strRunMode.equalsIgnoreCase("n")
						|| strRunMode.equalsIgnoreCase("no")) {

					MDP_MyCustomisedLogs.skipTestCase(strTestCaseID,
							strRunMode);

					continue;

				}
				MDP_MyCustomisedLogs.info(getBrowserDetails(capsDevices));

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	public static void getURLKoala() throws Exception {
		try {
			driver.get(strURL);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	// public SoftAssert softAssert = new SoftAssert();

	public static void waitImplictly(WebDriver dr) throws Exception {
		try {
			
			dr.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	public static WebElement waitExplicit(WebElement element) throws Exception {
		try {

			WebElement waitElement = new WebDriverWait(driver, 50)
					.until(ExpectedConditions.visibilityOf(element));
			return waitElement;

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error("Element not found " + e.toString());
			throw new Exception("Element not found " + e.toString());
		}

	}

	public static void isElementPresent(WebElement element) throws Exception {
		try {
			waitExplicit(element);
			if (element.isDisplayed()) {
				MDP_MyCustomisedLogs
						.info(element.getText() + "is present in the webpage");

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(element.getText()
					+ "is not present in the webpage" + e.toString());
			throw new Exception(element.getText()
					+ "is not present in the webpage" + e.toString());
		}

	}

	// retrieves the text of the element
	public static String getTextofElement(WebElement element) throws Exception {
		try {
			waitExplicit(element);
			MDP_MyCustomisedLogs
					.info(element.getText() + "is present in the webpage");
			return element.getText();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(element.getText()
					+ "is not present in the webpage" + e.toString());
			throw new Exception(element.getText()
					+ "is not present in the webpage" + e.toString());
		}

	}

	// send input values to text field
	public static void sendValues(WebElement element, String sendText)
			throws Exception {
		try {
			waitExplicit(element);
			element.clear();
			element.sendKeys(sendText);
			MDP_MyCustomisedLogs.info("Value entered in inputfield < "
					+ element.getText() + "> is < " + sendText + " > ");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error("unable to enter text < " + sendText
					+ " > in the element < " + element.getText() + " > "
					+ e.toString());
			throw new Exception("unable to enter text < " + sendText
					+ " > in the element < " + element.getText() + " > "
					+ e.toString());
		}

	}

	// get the count of elements for a particular string
	public static int getCountofElements(List<WebElement> elements, String string)
			throws Exception {
		try {

			MDP_MyCustomisedLogs.info(
					"Count in  <  " + string + " > is " + elements.size());
			return elements.size();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	// retrieve all the elements present in the webelements
	public static String getAllElements(List<WebElement> elements, String str)
			throws Exception {
		String sEleText = null;
		try {

			for (WebElement ele : elements) {
				waitExplicit(ele);
				if(ele.getText() != null) {
					MDP_MyCustomisedLogs
					.info("Elements in " + str + "are: " + sEleText);
			getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", sEleText);
					
				}
				
				
			}

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(
					sEleText + "is not present in the Menu navigation link "
							+ e.toString());
			throw new Exception(
					sEleText + "is not present in the Menu navigation link "
							+ e.toString());

		}
		return null;
	}

	// click on an element
	public static void clickOnElement(WebElement element) throws Exception {
		try {
			waitExplicit(element);
			element.click();
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error("Unable to click on the element < "
					+ element.getText() + " > " + e.toString());
			throw new Exception(e.toString());

		}

	}

	// gets the cookie
	public static void getCookie(WebElement element) throws Exception {
		try {
			waitExplicit(element);
			Set<org.openqa.selenium.Cookie> cookies = driver.manage()
					.getCookies();
			System.out.println("Size: " + cookies.size());
			Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
			while (itr.hasNext()) {
				org.openqa.selenium.Cookie cookie = itr.next();
				System.out.println(cookie.getName() + "::" + cookie.getPath()
						+ "<" + cookie.getDomain() + "> ||"
						+ cookie.getValue());

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());

		}
	}

	public static void selectThisElement(String string, List<WebElement> elements)
			throws Exception {
		try {

			for (WebElement ele : elements) {
				waitExplicit(ele);
				String str = string.toLowerCase();
				if (str.equalsIgnoreCase(ele.getText())) {
					ele.click();
				}
			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs
					.error(string + "is not present" + e.toString());
			throw new Exception(string + "is not present" + e.toString());
		}

	}

	private static void loginToEpiServer(String strCaps) throws Exception {
		try {
			 WebElement LOC_EPISERVER_LOGINID = driver.findElement(By.id("LoginControl_UserName"));
			 WebElement LOC_EPISERVER_PASSWORD = driver.findElement(By.id("LoginControl_Password"));
			 WebElement LOC_EPISERVER_SUBMITBTN = driver.findElement(By.id("LoginControl_Button1"));
			enterCredentials(LOC_EPISERVER_LOGINID, "atchuta.saragadam");
			
			enterCredentials(LOC_EPISERVER_PASSWORD, "June2016");
			
			waitExplicit(LOC_EPISERVER_SUBMITBTN);
			LOC_EPISERVER_SUBMITBTN.click();
			waitImplictly(driver);
			getScreenshot(strCaps, "EpiserverQA", "Pass", "");
			MDP_MyCustomisedLogs
					.info("Successfully logged in to the Episerver koalaqa ");

		} catch (Exception e) {
			getScreenshot(strCaps, "EpiserverQA", "Fail", "");
			MDP_MyCustomisedLogs.error(
					"unable to login to Episerver koalaqa" + e.toString());
			throw new Exception(
					"unable to login to Episerver koalaqa" + e.toString());
		}
	}
	
	public static void enterCredentials(WebElement eleCreds, String strCredsValue) throws Exception {
		waitExplicit(eleCreds);
		eleCreds.clear();
		eleCreds.sendKeys(strCredsValue);	
	}
}
