package au.com.mazda.MDP.TestCases;

import org.testng.annotations.Test;

import au.com.mazda.MDP.PageObjects_PF.MenuPf;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

// This test cases is to retrieve menu elements

public class MenuTc extends SuperClass {

	// test case to verify Menu items
	@Test(description = "verify Menu")
	public void testVerifyMenu() {
		try {
			MDP_MyCustomisedLogs.startTestCase(strTestName);

			MenuPf menuPF = new MenuPf(driver);
			getURLFromSpreadsheet();

			menuPF.executeMenu();
			// verifyLinks_Execute();
			getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", "");

			MDP_MyCustomisedLogs.endTestCase(strTestName);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}

	}

}
