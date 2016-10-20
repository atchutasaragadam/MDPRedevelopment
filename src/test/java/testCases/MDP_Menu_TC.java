package testCases;

import org.testng.TestException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageObjects_PF.MDP_Menu_PF;
import utility.MDP_MyCustomisedLogs;

public class MDP_Menu_TC extends MDP_SuperClass {

	@Test(description = "verify Menu")
	public void verify_Menu() throws Exception {

		try {

			MDP_MyCustomisedLogs.startTestCase("verify_Menu");
			test.log(LogStatus.INFO, "Started test case : < verify_Menu > ");

			MDP_Menu_PF menuPF = new MDP_Menu_PF(driver);
			getURLFromSpreadsheet();

			menuPF.execute_Menu();
			// verifyLinks_Execute();
			getScreenshot(driver, "verify_Menu", "PASS", "");

			MDP_MyCustomisedLogs.endTestCase("verify_Menu");
			test.log(LogStatus.INFO, "Ended test case : < verify_Menu >");

		} catch (Exception e) {

			getScreenshot(driver, "verify_Menu", "FAIL", "");
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());

		}

	}

}
