package utility;

public class MDP_Constant {

	public static String ExcelFilePath = System.getProperty("user.dir")
			+ "/src/test/java/TestData/MDP_TestCases_TestData.xlsx";

	public static String SheetTestCases = "TestCases";

	public static String SheetTestData = "TestData";

	public static String SheetPostCodePopUp = "PostCode";

	public static int Col_TestCaseID = 0;

	public static int Col_RunMode = 1;

	public static int Col_URL = 2;

	public static int Col_SectionName = 3;

	public static int CoL_Paragraph1 = 4;

	public static int CoL_Paragraph2 = 5;

	public static int CoL_Paragraph3 = 6;

	public static int Col_PostCode = 3;

	public static String ScreenshotPath = System.getProperty("user.dir") + "/screenshots/";

	public static String strVirtualBoxScriptsPath = System.getProperty("user.dir") + "/RunVirtualBoxScripts/";

	public static String strSeleniumGridShellScriptPath = System.getProperty("user.dir")
			+ "/SeleniumGrid/SeleniumGrid_3.0.0-beta2/bashScript_SeleniumGrid.sh";

	public static String Logs_HTML = System.getProperty("user.dir")
			+ "/src/test/java/Logs_HTML/MAZD0348_MDP_Redevelopment_AutomationReports.html";

	public static final String BS_USERNAME = "cheproximity";
	public static final String BS_AUTOMATE_KEY = "j3Hepy4RMEvPez9bNKhP";
	public static final String BS_URL = "https://" + BS_USERNAME + ":" + BS_AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public static final String str_NodeURL = "http://127.0.0.1:4444";

	public static final String str_KoalaURL = "http://localhost:3200/";

	// public static final String selenium_grid_Localhost =
	// "http://169.254.248.55:5566/wd/hub";
	// public static final String selenium_grid_Localhost =
	// "http://10.0.0.38:5555/wd/hub";
	// public static final String selenium_grid_Localhost =
	// "http://localhost:5555/wd/hub";
	// public static final String
	// selenium_grid_Localhost="http://10.0.2.15:5566/wd/hub";
	// public static final String
	// selenium_grid_Localhost="http://192.168.56.101:5566/wd/hub";
	public static final String selenium_grid_Localhost = "http://192.168.32.34:5566/wd/hub";

}
