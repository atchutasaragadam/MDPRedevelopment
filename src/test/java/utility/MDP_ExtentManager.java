package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class MDP_ExtentManager {

	public static ExtentReports Instance() {

		ExtentReports extent;
		System.out.println("Path for extent reports : < " + MDP_Constant.Logs_HTML + " >");
		extent = new ExtentReports(MDP_Constant.Logs_HTML, false);

		return extent;
	}

	public static String CaptureScreenExtent(WebDriver driver, String ImagesPath) {
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath + ".jpg");

		try {
			FileUtils.copyFile(oScnShot, oDest);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return ImagesPath + ".jpg";
	}

}
