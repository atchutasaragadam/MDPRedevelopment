package testCases;

import java.util.concurrent.TimeUnit;

import org.testng.TestException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.MDP_Constant;
import utility.MDP_Excel;
import utility.MDP_MyCustomisedLogs;

public class MDP_VerifyContentInAPage_TC extends MDP_SuperClass {

	@Test(description = " Verifying content ")
	public void verify_Content() throws Exception {
		
		try {

			MDP_MyCustomisedLogs.startTestCase("verify_Content");
			test.log(LogStatus.INFO, "Started test case : < verify_Content > ");

			verifyTextPresent_Execute();

			MDP_MyCustomisedLogs.endTestCase("verify_Content");
			test.log(LogStatus.INFO, "Ended test case : < verify_Content > ");

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());

		}
	}

	public void verifyTextPresent_Execute() throws Exception {

		String strTestCaseID;
		String strRunMode;
		String strURL;
		String strSectionName;

		try {

			int i_RowCount = MDP_Excel.getRowCount(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData);

			for (int i = 1; i <= i_RowCount; i++) {

				int i_ColCount = MDP_Excel.getColumnCount(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData, i);
				strTestCaseID = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData, i,
						MDP_Constant.Col_TestCaseID);
				strRunMode = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData, i,
						MDP_Constant.Col_RunMode);
				if (strRunMode.equalsIgnoreCase("y") || strRunMode.equalsIgnoreCase("yes")) {

					MDP_MyCustomisedLogs.startTestCase(strTestCaseID);
					test.log(LogStatus.INFO, "Starting Test case: <  " + strTestCaseID + " >");

					MDP_MyCustomisedLogs.info("Starting the test case < " + strTestCaseID
							+ " > as the Run Mode for this test case is < " + strRunMode + " >");
					test.log(LogStatus.INFO, "Starting the test case < " + strTestCaseID
							+ " > as the Run Mode for this test case is < " + strRunMode + " >");

					System.out.println();

					strURL = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData, i,
							MDP_Constant.Col_URL);
					strSectionName = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath, MDP_Constant.SheetTestData, i,
							MDP_Constant.Col_SectionName);

					driver.get(strURL);

					MDP_MyCustomisedLogs.info("navigated to " + strURL);
					test.log(LogStatus.INFO, "navigated to " + strURL);

					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					for (int j = 4; j < i_ColCount; j++) {
						String strExoectedTextFromExcel = MDP_Excel.getCellValue(MDP_Constant.ExcelFilePath,
								MDP_Constant.SheetTestData, i, j);
						verifyGetPageSource(strSectionName, strExoectedTextFromExcel);
					}

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

	public void verifyGetPageSource(String strSectionName, String strExpectedTextFromExcel) throws Exception {
		try {

			MDP_MyCustomisedLogs.info("In Section : < " + strSectionName + " >");
			test.log(LogStatus.INFO, "In Section : < " + strSectionName + " >");

			MDP_MyCustomisedLogs.info("Expected Text in section < " + strSectionName + " >  from excel is < "
					+ strExpectedTextFromExcel + " > ");
			test.log(LogStatus.INFO, "Expected Text in section < " + strSectionName + " >  from excel is < "
					+ strExpectedTextFromExcel + " > ");

			if (!strExpectedTextFromExcel.equals("")) {

				boolean b = driver.getPageSource().contains(strExpectedTextFromExcel);

				if (b) {

					MDP_MyCustomisedLogs.info("Actual and Expected are same");
					test.log(LogStatus.INFO, "Actual and Expected are same");

				} else {

					MDP_MyCustomisedLogs.info("Actual and Expected are not same");
					test.log(LogStatus.INFO, "Actual and Expected are not same");

				}
			}

		} catch (Exception e) {

			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.INFO, e.toString());
			throw new TestException(e.toString());

		}
	}

}
