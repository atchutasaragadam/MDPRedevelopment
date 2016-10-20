package utility;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import testCases.MDP_SuperClass;

public class MDP_MyCustomisedLogs extends MDP_SuperClass {

	private static Logger myLog = Logger.getLogger(MDP_MyCustomisedLogs.class.getName());

	public static void debug(String message) {

		myLog.debug(message);
		System.out.println(message);
		Reporter.log(message);

	}

	// end test case
	public static void endTestCase(String sTestCaseID) {
		myLog.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "Ends Test Case ID " + sTestCaseID
				+ "             XXXXXXXXXXXXXXXXXXXXXX");
		myLog.info("X");
		myLog.info("X");
		myLog.info("X");
		myLog.info("X");

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXX             " + "Ends Test Case ID " + sTestCaseID
				+ "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("X");
		System.out.println("X");
		System.out.println("X");
		System.out.println("X");

		Reporter.log("XXXXXXXXXXXXXXXXXXXXXXX             " + "Ends Test Case ID " + sTestCaseID
				+ "             XXXXXXXXXXXXXXXXXXXXXX");
		Reporter.log("X");
		Reporter.log("X");
		Reporter.log("X");
		Reporter.log("X");

	}

	public static void error(String message) {

		myLog.error(message);
		System.out.println(message);
		Reporter.log(message);

	}

	public static void fatal(String message) {

		myLog.fatal(message);
		System.out.println(message);
		Reporter.log(message);

	}

	// giving information about the test case
	public static void info(String message) {

		myLog.info(message);
		System.out.println(message);
		Reporter.log(message);

	}

	// skip test case
	public static void skipTestCase(String sTestCaseID, String message) {

		myLog.info("XXXXXXXXXXXXXXXXXXXXXXX             " + " Test case ID " + sTestCaseID
				+ " is skipped            XXXXXXXXXXXXXXXXXXXXXX. The Reason for skipping test case is  < " + message
				+ " >");
		myLog.info("X");
		myLog.info("X");
		myLog.info("X");
		myLog.info("X");

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXX             " + " Test case ID " + sTestCaseID
				+ " is skipped          XXXXXXXXXXXXXXXXXXXXXX. The Reason for skipping test case is  < " + message
				+ " >");
		System.out.println("X");
		System.out.println("X");
		System.out.println("X");
		System.out.println("X");

		Reporter.log("XXXXXXXXXXXXXXXXXXXXXXX             " + " Test case ID " + sTestCaseID
				+ " is skipped             XXXXXXXXXXXXXXXXXXXXXX. The Reason for skipping test case is  < " + message
				+ " >");
		Reporter.log("X");
		Reporter.log("X");
		Reporter.log("X");
		Reporter.log("X");

	}

	// to print log for the beginning of the test case, to know which test case
	// is being executed
	public static void startTestCase(String sTestCaseID) {

		myLog.info("**********************************************************************************");
		myLog.info("**********************************************************************************");
		myLog.info("$$$$$$$$$$$     Starts executing Test case ID        " + sTestCaseID + "          $$$$$$$$$$$$$");
		myLog.info("**********************************************************************************");
		myLog.info("**********************************************************************************");

		System.out.println("**********************************************************************************");
		System.out.println("**********************************************************************************");
		System.out.println(
				"$$$$$$$$$$$    Starts executing Test case ID          " + sTestCaseID + "          $$$$$$$$$$$$$");
		System.out.println("**********************************************************************************");
		System.out.println("**********************************************************************************");

		Reporter.log("**********************************************************************************");
		Reporter.log("**********************************************************************************");
		Reporter.log(
				"$$$$$$$$$$$      Starts executing Test case ID        " + sTestCaseID + "          $$$$$$$$$$$$$");
		Reporter.log("**********************************************************************************");
		Reporter.log("**********************************************************************************");

	}

	public static void warn(String message) {

		myLog.warn(message);
		System.out.println(message);
		Reporter.log(message);

	}

}
