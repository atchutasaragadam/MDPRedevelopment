package au.com.mazda.MDP.TestCases;

import org.testng.annotations.Test;

import au.com.mazda.MDP.PageObjects_PF.GalleryGrid_Pf;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class GalleryGrid_Tc extends SuperClass {

	@Test(description = "verifying Gallery Grid elements are present or not")
	public void testVerifyGalleryGridPresence() throws Exception {
		try {
			MDP_MyCustomisedLogs.startTestCase(strTestName);

			GalleryGrid_Pf gallery_PF = new GalleryGrid_Pf(driver);
			driver.get(
					"http://koalaqa.azurewebsites.net/module-library/content-modules/");
			// getURLFromSpreadsheet();
			// driver.get("http://localhost:3200/vehicles/mazda-3/gallery");
			// driver.get("http://koalaqa.azurewebsites.net/#");
			// driver.get(
			// "http://koala.node.chdigital.com.au/vehicles/mazda-3/gallery");
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());

			// softAssert.assertEquals("xcy", "xcy" );
			gallery_PF.verifyGalleryInNavPage();
			getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", "");
//			getScreenshot(driver, str_DriverBrowserName, str_DriverOS,
//					str_DriverVersion, strTestName, "PASS", "");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

	@Test(description = "verifying Gallery Grid images are present or not")
	public void testVerifyImagesPresence() throws Exception {
		try {
			MDP_MyCustomisedLogs.startTestCase(strTestName);

			GalleryGrid_Pf gallery_PF = new GalleryGrid_Pf(driver);
			getURLFromSpreadsheet();
			// driver.get("http://localhost:3200/vehicles/mazda-3/gallery");
			// driver.get("http://koalaqa.azurewebsites.net/#");
			// driver.get(
			// "http://koala.node.chdigital.com.au/vehicles/mazda-3/gallery");
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());

			// softAssert.assertEquals("xcy", "xcy" );
			gallery_PF.verifyGalleryImgs();
			getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", "");
//			getScreenshot(driver, str_DriverBrowserName, str_DriverOS,
//					str_DriverVersion, strTestName, "PASS", "");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

	@Test(description = "verifying Gallery Grid videos are present or not")
	public void testVerifyVideosPresence() throws Exception {
		try {
			MDP_MyCustomisedLogs.startTestCase(strTestName);

			GalleryGrid_Pf gallery_PF = new GalleryGrid_Pf(driver);
			getURLFromSpreadsheet();
			// driver.get("http://localhost:3200/vehicles/mazda-3/gallery");
			// driver.get("http://koalaqa.azurewebsites.net/#");
			// driver.get(
			// "http://koala.node.chdigital.com.au/vehicles/mazda-3/gallery");
			MDP_MyCustomisedLogs.info("navigated to " + driver.getCurrentUrl());

			// softAssert.assertEquals("xcy", "xcy" );
			gallery_PF.verifyGalleryVideos();
			getScreenshot(getBrowserDetails(capsDevices), strTestName, "Pass", "");
//			getScreenshot(driver, str_DriverBrowserName, str_DriverOS,
//					str_DriverVersion, strTestName, "PASS", "");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}
}
