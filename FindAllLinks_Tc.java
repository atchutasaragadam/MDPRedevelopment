package au.com.mazda.MDP.TestCases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import au.com.mazda.MDP.Utility.MDP_Constant;
import au.com.mazda.MDP.Utility.MDP_Excel;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

/**
 * 
 * @author cheaus
 *
 */
public class FindAllLinks_Tc extends SuperClass {

	public static int linkcount;

	public static String linkText;
	static boolean bIsValid = false;

	public static void getLinksResponse(String strTestCaseId, String strUrl)
			throws Exception {

		try {

			MDP_MyCustomisedLogs
					.info("Getting  Total child links for  " + strTestCaseId);

			linkcount = driver.findElements(By.tagName("a")).size();
			MDP_MyCustomisedLogs.info(
					"Total number of links is/are : < " + linkcount + " >");

			for (int i = 0; i < linkcount; i++) {
				linkText = driver.findElements(By.tagName("a")).get(i)
						.getText();
				String url = driver.findElements(By.tagName("a")).get(i)
						.getAttribute("href");

				MDP_MyCustomisedLogs.info("");

				if (!linkText.equals("")) {
					MDP_MyCustomisedLogs.info(
							"    Now testing for link text   " + linkText);

					MDP_MyCustomisedLogs
							.info("     Now testing for url    " + url);

				} else {
					MDP_MyCustomisedLogs.info("     URL is blank    " + url);

				}

				if (url != null) {
					bIsValid = getResponseCode(url, strTestCaseId);

					if (bIsValid) {

						MDP_MyCustomisedLogs.info("Response Status is < OK > ");

						MDP_MyCustomisedLogs
								.info("Valid link: <<  " + url + "  >>");

						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(5,
								TimeUnit.SECONDS);

						if (!linkText.equals("")) {
							driver.findElements(By.tagName("a")).get(i).click();
							waitImplictly(driver);
							driver.manage().timeouts().implicitlyWait(5,
									TimeUnit.SECONDS);
							MDP_MyCustomisedLogs.info("Clicked on :  < "
									+ linkText + "  > and the URL is < " + url
									+ " > ");

						} else {
							waitImplictly(driver);
							// ((JavascriptExecutor)
							// driver).executeScript("window.scrollTo(0,"
							// +
							// driver.findElements(By.tagName("a")).get(i).getLocation().y
							// + ")");

							// driver.findElements(By.tagName("a")).get(i).click();
							driver.get(url);
							waitImplictly(driver);
							driver.manage().timeouts().implicitlyWait(5,
									TimeUnit.SECONDS);
							MDP_MyCustomisedLogs
									.info("Clicked on URL is < " + url + " > ");

						}
						getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", linkText);
//						getScreenshot(driver, str_DriverBrowserName,
//								str_DriverOS, str_DriverVersion, strTestCaseId,
//								"PASS", linkText);

					} else {

						MDP_MyCustomisedLogs
								.info("Broken link: <<  " + url + "  >>");

					}
				} else {

					MDP_MyCustomisedLogs.info("No URL for" + linkText);

					continue;
				}
			}

		} catch (Exception e) {

			getScreenshot(getBrowserDetails(capsDevices), strTestName, "FAIL", linkText);
//			getScreenshot(driver, str_DriverBrowserName, str_DriverOS,
//					str_DriverVersion, strTestCaseId, "FAIL", linkText);

			MDP_MyCustomisedLogs.info(e.toString());

		}
	}

	public static boolean getResponseCode(String sCheckUrl,
			String strTestCaseID) throws Exception {
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

			MDP_MyCustomisedLogs
					.info("HTTP Response Code is : [ " + iResp_Code + " ]");

			MDP_MyCustomisedLogs
					.info("HTTP Response Status is : [ " + sResp_Status + " ]");

			if ((iResp_Code == 404) || (iResp_Code == 505)) {
				bValidResponse = false;
			} else {
				bValidResponse = true;
			}

		} catch (Exception e) {

			getScreenshot(getBrowserDetails(capsDevices), strTestName, "FAIL", linkText);
//			getScreenshot(driver, str_DriverBrowserName, str_DriverOS,
//					str_DriverVersion, strTestCaseID, "FAIL", linkText);

			MDP_MyCustomisedLogs.error(e.toString()
					+ "|| description of the error : Response code is < "
					+ iResp_Code + " > with response status is: < "
					+ sResp_Status);

		}
		return bValidResponse;

	}

	// actual test case begins to verify and validate links
	@Test(description = "verifying  and validating links")
	public void testVerifyLinksCampaigns() throws Exception {

		MDP_MyCustomisedLogs.startTestCase(strTestName);

		verifyLinks_Execute();

		MDP_MyCustomisedLogs.endTestCase(strTestName);

	}

	// called method to verifying links
	public void verifyLinks_Execute() throws Exception {

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

					String strURL = MDP_Excel.getCellValue(
							MDP_Constant.S_EXCEL_FILE_PATH,
							MDP_Constant.S_SHEET_TEST_CASES, i,
							MDP_Constant.I_COL_URL);

					driver.get(strURL);

					MDP_MyCustomisedLogs.info("navigated to " + strURL);

					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(20,
							TimeUnit.SECONDS);
					getLinksResponse(strTestCaseID, strURL);

				} else {

					MDP_MyCustomisedLogs.skipTestCase(strTestCaseID,
							"Skipping the test case < " + strTestCaseID
									+ " > as the Run Mode for this test case is < "
									+ strRunMode + " >");

				}

			}
		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
		}

	}

}
