package au.com.mazda.MDP.TestCases;

import org.testng.annotations.Test;

import au.com.mazda.MDP.PageObjects_PF.Configurator_PF;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class Configurator_Tc extends SuperClass {

	@Test(description = "Retreiving Headers in Configurator Page")
	public void testHeadersPresenceConfigurator() throws Exception {
		try {
			MDP_MyCustomisedLogs.startTestCase(strTestName);

			Configurator_PF config_PF = new Configurator_PF(driver);
			driver.get(
					"http://koalaqa.azurewebsites.net/module-library/content-modules/");
			// getURLFromSpreadsheet();
			// driver.get("http://localhost:3200/vehicles/mazda-3/gallery");
			// driver.get("http://koalaqa.azurewebsites.net/#");
			// driver.get(
			// "http://koala.node.chdigital.com.au/vehicles/mazda-3/gallery");
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());

			// softAssert.assertEquals("xcy", "xcy" );
			config_PF.execute_Configurator();
			//getScreenshot(strTestName, "PASS", "");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

}
