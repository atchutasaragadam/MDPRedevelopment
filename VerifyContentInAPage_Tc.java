package au.com.mazda.MDP.TestCases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import au.com.mazda.MDP.Utility.MDP_Constant;
import au.com.mazda.MDP.Utility.MDP_Excel;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class VerifyContentInAPage_Tc extends SuperClass {

	@Test(description = " Verifying content ")
	public void testVerifyContent() throws Exception {

		MDP_MyCustomisedLogs.startTestCase("verify_Content");
		verifyTextPresent_Execute();
		MDP_MyCustomisedLogs.endTestCase("verify_Content");

	}

	public void verifyGetPageSource(String strSectionName,
			String strExpectedTextFromExcel) throws Exception {
		try {

			MDP_MyCustomisedLogs
					.info("In Section : < " + strSectionName + " >");

			MDP_MyCustomisedLogs.info("Expected Text in section < "
					+ strSectionName + " >  from excel is < "
					+ strExpectedTextFromExcel + " > ");

			if (!strExpectedTextFromExcel.equals("")) {

				boolean b = driver.getPageSource()
						.contains(strExpectedTextFromExcel);

				if (b) {

					MDP_MyCustomisedLogs.info("Actual and Expected are same");

				} else {

					MDP_MyCustomisedLogs
							.info("Actual and Expected are not same");

				}
			}

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());

		}
	}

	public void verifyTextPresent_Execute() throws Exception {

		String strTestCaseId;
		String strRunMode;

		String strSectionName;

		try {

			int i_RowCount = MDP_Excel.getRowCount(
					MDP_Constant.S_EXCEL_FILE_PATH,
					MDP_Constant.S_SHEET_TEST_DATA);

			for (int i = 1; i <= i_RowCount; i++) {

				int i_ColCount = MDP_Excel.getColumnCount(
						MDP_Constant.S_EXCEL_FILE_PATH,
						MDP_Constant.S_SHEET_TEST_DATA, i);
				strTestCaseId = MDP_Excel.getCellValue(
						MDP_Constant.S_EXCEL_FILE_PATH,
						MDP_Constant.S_SHEET_TEST_DATA, i,
						MDP_Constant.I_COL_TEST_CASE_ID);
				strRunMode = MDP_Excel.getCellValue(
						MDP_Constant.S_EXCEL_FILE_PATH,
						MDP_Constant.S_SHEET_TEST_DATA, i,
						MDP_Constant.I_COL_RUN_MODE);
				if (strRunMode.equalsIgnoreCase("y")
						|| strRunMode.equalsIgnoreCase("yes")) {

					MDP_MyCustomisedLogs.startTestCase(strTestCaseId);

					MDP_MyCustomisedLogs
							.info("Starting the test case < " + strTestCaseId
									+ " > as the Run Mode for this test case is < "
									+ strRunMode + " >");

					System.out.println();

					strURL = MDP_Excel.getCellValue(
							MDP_Constant.S_EXCEL_FILE_PATH,
							MDP_Constant.S_SHEET_TEST_DATA, i,
							MDP_Constant.I_COL_URL);
					strSectionName = MDP_Excel.getCellValue(
							MDP_Constant.S_EXCEL_FILE_PATH,
							MDP_Constant.S_SHEET_TEST_DATA, i,
							MDP_Constant.I_COL_SECTION_NAME);

					driver.get(strURL);

					MDP_MyCustomisedLogs.info("navigated to " + strURL);

					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(5,
							TimeUnit.SECONDS);
					for (int j = 4; j < i_ColCount; j++) {
						String strExoectedTextFromExcel = MDP_Excel
								.getCellValue(MDP_Constant.S_EXCEL_FILE_PATH,
										MDP_Constant.S_SHEET_TEST_DATA, i, j);
						verifyGetPageSource(strSectionName,
								strExoectedTextFromExcel);
					}

				} else {

					MDP_MyCustomisedLogs.skipTestCase(strTestCaseId,
							"Skipping the test case < " + strTestCaseId
									+ " > as the Run Mode for this test case is < "
									+ strRunMode + " >");

				}
			}

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());

		}
	}

}
