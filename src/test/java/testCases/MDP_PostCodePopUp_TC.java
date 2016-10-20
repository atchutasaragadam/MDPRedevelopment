package testCases;



import org.testng.TestException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageObjects_PF.MDP_PostCodePopp_PF;
import utility.MDP_Constant;
import utility.MDP_Excel;
import utility.MDP_MyCustomisedLogs;

public class MDP_PostCodePopUp_TC extends MDP_SuperClass {

	@Test(description = "verifying post code icon is present or not")
	public void verify_PostCodePopupPresence() throws Exception {
		
		try {

			MDP_MyCustomisedLogs.startTestCase("verify_PostCodePopupPresence");
			test.log(LogStatus.INFO, "Started test case : < verify_PostCodePopupPresence >");

			MDP_PostCodePopp_PF postcode_PF = new MDP_PostCodePopp_PF(driver);
			getURLKoala();
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());
			test.log(LogStatus.INFO, "navigated to " + driver.getCurrentUrl());

			postcode_PF.execute_PostCode_PopUP();
			getScreenshot(driver, "verify_PostCodePopupPresence", "PASS", "");

		} catch (Exception e) {

			getScreenshot(driver, "verify_PostCodePopupPresence", "FAIL", "");
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());
		}
	}

	@Test(description = "verifying close on post code pop up")
	public void verify_ClosePostCodePopUp() throws Exception {
		
		try {

			MDP_MyCustomisedLogs.startTestCase("verify_ClosePostCodePopUp");
			test.log(LogStatus.INFO, "Started test case :" + "verify_ClosePostCodePopUp");

			MDP_PostCodePopp_PF postcode_PF = new MDP_PostCodePopp_PF(driver);
			getURLKoala();
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());
			test.log(LogStatus.INFO, "navigated to " + driver.getCurrentUrl());

			driver.navigate().refresh();
			postcode_PF.execute_PostCodeUP_Close();
			getScreenshot(driver, "verify_ClosePostCodePopUp", "PASS", "");

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());
		}
	}

	@Test(description = "Post Code Pop Up")
	public void verify_PostCodePopUp() throws Exception {
		
		try {

			MDP_MyCustomisedLogs.startTestCase("verify_PostCodePopUp");
			test.log(LogStatus.INFO, "Started test case : < verify_PostCodePopUp >");

			MDP_PostCodePopp_PF postcode_PF = new MDP_PostCodePopp_PF(driver);
			getURLKoala();
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());
			test.log(LogStatus.INFO, "navigated to " + driver.getCurrentUrl());

			int i_RowCount = MDP_Excel.getRowCount(MDP_Constant.ExcelFilePath, MDP_Constant.SheetPostCodePopUp);

			for (int i = 1; i <= i_RowCount; i++) {

				String TestCaseID = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetPostCodePopUp,
						i, MDP_Constant.Col_TestCaseID);
				String sRunMode = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetPostCodePopUp, i,
						MDP_Constant.Col_RunMode);
				if (sRunMode.equalsIgnoreCase("yes") || sRunMode.equalsIgnoreCase("y")) {
					MDP_MyCustomisedLogs.startTestCase(TestCaseID);
					test.log(LogStatus.INFO, "Starting Test case: <  " + TestCaseID + " >");

					String sPostCode = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath,
							MDP_Constant.SheetPostCodePopUp, i, MDP_Constant.Col_PostCode);

					if (sPostCode.length() == 4) {
						int[] postcodeArray = new int[4];
						for (int i1 = 0; i1 < sPostCode.length(); i1++) {
							if (Character.isDigit(sPostCode.charAt(i1))) {
								postcodeArray[i1] = sPostCode.charAt(i1);

							}

						}
						if (postcodeArray.length > 0) {
							driver.navigate().refresh();
							postcode_PF.execute_PostCodeUP_Submit(sPostCode);
							getScreenshot(driver, TestCaseID, "PASS", sPostCode);
						} else {

							MDP_MyCustomisedLogs
									.info("Not executing the post code as the value from excel is : " + sPostCode);
							test.log(LogStatus.INFO,
									"Not executing the post code as the value from excel is : " + sPostCode);

							getScreenshot(driver, "verify_PostCodePopUp", "PASS", sPostCode);
						}

					} else {

						MDP_MyCustomisedLogs
								.info("Not executing the post code as the value from excel is : " + sPostCode);
						test.log(LogStatus.INFO,
								"Not executing the post code as the value from excel is : " + sPostCode);
						getScreenshot(driver, " verifyPostCodePopUp ", "PASS", sPostCode);
					}

					MDP_MyCustomisedLogs.endTestCase("Post Code Pop Up");
					test.log(LogStatus.INFO, "Ended test case : < Post Code Pop Up > ");

					getScreenshot(driver, "verify_PostCodePopUp", "PASS", sPostCode);

				} else {
					MDP_MyCustomisedLogs.skipTestCase(TestCaseID, "Skipping the test case < " + TestCaseID
							+ " > as the Run Mode for this test case is < " + sRunMode + " >");

					test.log(LogStatus.SKIP, "Skipping the test case < " + TestCaseID
							+ " > as the Run Mode for this test case is < " + sRunMode + " >");

					getScreenshot(driver, "verify_PostCodePopUp", "PASS", "");
				}
			}

		} catch (Exception e) {

			getScreenshot(driver, "verify_PostCodePopUp", "PASS", "");

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());

		}
	}
	
	@Test(description = "Get cookie for post code ") 
		public void verify_getCookiePostCode() throws Exception {
			//execute_getCookiePostCode
			
			try {

				MDP_MyCustomisedLogs.startTestCase("verify_GetCookiePostCode");
				test.log(LogStatus.INFO, "Started test case : < verify_GetCookiePostCode >");

				MDP_PostCodePopp_PF postcode_PF = new MDP_PostCodePopp_PF(driver);
				getURLKoala();
				MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());
				test.log(LogStatus.INFO, "navigated to " + driver.getCurrentUrl());

				postcode_PF.execute_getCookiePostCode();
				getScreenshot(driver, "verify_GetCookiePostCode", "PASS", "");

			} catch (Exception e) {

				getScreenshot(driver, "verify_GetCookiePostCode", "FAIL", "");
				MDP_MyCustomisedLogs.error(e.toString());
				test.log(LogStatus.ERROR, e.toString());
				throw new TestException(e.toString());
			}
			
		}
		
	}


