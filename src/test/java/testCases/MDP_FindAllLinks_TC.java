package testCases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.TestException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.MDP_Constant;
import utility.MDP_Excel;
import utility.MDP_MyCustomisedLogs;

public class MDP_FindAllLinks_TC extends MDP_SuperClass {

	public String strClassName;

	public static int linkcount;
	public static String linkText;

	static boolean bIsValid = false;

	@Test(description = "verifying  and validating links")
	public void verify_LinksCampaigns() throws Exception {
		try {

			MDP_MyCustomisedLogs.startTestCase("verify_LinksCampaigns");
			test.log(LogStatus.INFO, "Started test case : < verify_LinksCampaigns > ");

			verifyLinks_Execute();

			MDP_MyCustomisedLogs.endTestCase("verify_LinksCampaigns");
			test.log(LogStatus.INFO, "Ended test case : < verify_LinksCampaigns > ");

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

		}
	}

	public void verifyLinks_Execute() throws Exception {

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

					driver.get(strURL);

					MDP_MyCustomisedLogs.info("navigated to " + strURL);
					test.log(LogStatus.INFO, "navigated to " + strURL);

					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					getLinksResponse(strTestCaseID, strURL);
					test.log(LogStatus.INFO, "Ended test case :  <  " + strTestCaseID + " >");

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

	public static void getLinksResponse(String strTestCaseID, String strURL) throws Exception {

		try {

			MDP_MyCustomisedLogs.info("Getting  Total child links for  " + strTestCaseID);
			test.log(LogStatus.INFO, "Getting  Total child links for  " + strTestCaseID);

			linkcount = driver.findElements(By.tagName("a")).size();
			MDP_MyCustomisedLogs.info("Total number of links is/are : < " + linkcount + " >");
			test.log(LogStatus.INFO, "Total number of links is/are : < " + linkcount + " >");

			for (int i = 0; i < linkcount; i++) {
				linkText = driver.findElements(By.tagName("a")).get(i).getText();
				String url = driver.findElements(By.tagName("a")).get(i).getAttribute("href");

				MDP_MyCustomisedLogs.info("");

				if (!linkText.equals("")) {
					MDP_MyCustomisedLogs.info("    Now testing for link text   " + linkText);
					test.log(LogStatus.INFO, "   Now testing for link text      " + linkText);

					MDP_MyCustomisedLogs.info("     Now testing for url    " + url);
					test.log(LogStatus.INFO, "     Now testing for url    " + url);

				} else {
					MDP_MyCustomisedLogs.info("     Now testing for url    " + url);
					test.log(LogStatus.INFO, "     Now testing for url    " + url);

				}

				if (url != null) {
					bIsValid = getResponseCode(url, strTestCaseID);

					if (bIsValid) {

						MDP_MyCustomisedLogs.info("Response Status is < OK > ");
						test.log(LogStatus.INFO, "Response Status is < OK > ");

						MDP_MyCustomisedLogs.info("Valid link: <<  " + url + "  >>");
						test.log(LogStatus.INFO, "Valid link: <<  " + url + "  >>");

						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

						if (!linkText.equals("")) {
							driver.findElements(By.tagName("a")).get(i).click();
							Thread.sleep(1000);
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							MDP_MyCustomisedLogs
									.info("Clicked on :  < " + linkText + "  > and the URL is < " + url + " > ");
							test.log(LogStatus.INFO,
									"Clicked on :  < " + linkText + "  > and the URL is < " + url + " > ");

						} else {
							Thread.sleep(2000);
							// ((JavascriptExecutor)
							// driver).executeScript("window.scrollTo(0,"
							// +
							// driver.findElements(By.tagName("a")).get(i).getLocation().y
							// + ")");

							// driver.findElements(By.tagName("a")).get(i).click();
							driver.get(url);
							Thread.sleep(1000);
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							MDP_MyCustomisedLogs.info("Clicked on URL is < " + url + " > ");
							test.log(LogStatus.INFO, "Clicked on URL is < " + url + " > ");

						}

						getScreenshot(driver, strTestCaseID, "PASS", linkText);

					} else {

						MDP_MyCustomisedLogs.info("Broken link: <<  " + url + "  >>");
						test.log(LogStatus.INFO, "Broken link: <<  " + url + "  >>");

					}
				} else {

					MDP_MyCustomisedLogs.info("No URL for" + linkText);
					test.log(LogStatus.INFO, "No URL for" + linkText);

					continue;
				}
			}

		} catch (Exception e) {

			getScreenshot(driver, strTestCaseID, "FAIL", linkText);

			MDP_MyCustomisedLogs.info(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());

		}
	}

	public static boolean getResponseCode(String sCheckUrl, String strTestCaseID) throws Exception {
		boolean bValidResponse = false;
		int iResp_Code = 0;
		String sResp_Status = null;
		try {

			URL u = new URL(sCheckUrl);
			HttpURLConnection httpCon = (HttpURLConnection) u.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setConnectTimeout(5000);

			httpCon.connect();
			sResp_Status = httpCon.getResponseMessage();
			iResp_Code = httpCon.getResponseCode();

			MDP_MyCustomisedLogs.info("HTTP Response Code is : [ " + iResp_Code + " ]");
			test.log(LogStatus.INFO, "HTTP Response Code is : [ " + iResp_Code + " ]");

			MDP_MyCustomisedLogs.info("HTTP Response Status is : [ " + sResp_Status + " ]");
			test.log(LogStatus.INFO, "HTTP Response Status is : [ " + sResp_Status + " ]");

			if ((iResp_Code == 404) || (iResp_Code == 505)) {
				bValidResponse = false;
			} else {
				bValidResponse = true;
			}

		} catch (Exception e) {

			getScreenshot(driver, strTestCaseID, "FAIL", linkText);

			MDP_MyCustomisedLogs.error(e.toString() + "|| description of the error : Response code is < " + iResp_Code
					+ " > with response status is: < " + sResp_Status);
			test.log(LogStatus.ERROR, e.toString() + "|| description of the error : Response code is < " + iResp_Code
					+ " > with response status is: < " + sResp_Status);
			throw new TestException(e.toString() + "|| description of the error : Response code is < " + iResp_Code
					+ " > with response status is: < " + sResp_Status);

		}
		return bValidResponse;

	}

}
