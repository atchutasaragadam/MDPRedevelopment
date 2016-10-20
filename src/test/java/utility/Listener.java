package utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	// This will execute before every method including
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String textMsg = "About to begin executing following method : " + returnMethodName(method.getTestMethod());
		Reporter.log(textMsg, true);

	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		String textMsg = "Completed executing following method : " + returnMethodName(method.getTestMethod());
		Reporter.log(textMsg, true);

	}

	public void onStart(ISuite suite) {
		Reporter.log(" About to begin executing suite " + suite.getName(), true);

	}

	public void onFinish(ISuite suite) {
		Reporter.log(" About to end executing Suite " + suite.getName(), true);

	}

	public void onTestStart(ITestResult result) {
		Reporter.log("The execution of the main test starts now");

	}

	public void onTestSuccess(ITestResult result) {
		printTestResults(result);

	}

	public void onTestFailure(ITestResult result) {
		printTestResults(result);

	}

	public void onTestSkipped(ITestResult result) {
		printTestResults(result);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		printTestResults(result);
	}

	public void onStart(ITestContext context) {
		Reporter.log(" About to begin executing Test " + context.getName(), true);

	}

	public void onFinish(ITestContext context) {
		Reporter.log("Completed executing test " + context.getName(), true);

	}

	// This method will be executed in case of test pass or fail or skip. This
	// will provide the information of the test
	private void printTestResults(ITestResult result) {
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}

			Reporter.log("Test Method had the following parameters : " + params, true);
		}

		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "PASSED";
			break;

		case ITestResult.FAILURE:
			status = "FAILED";
			break;

		case ITestResult.SKIP:
			status = "SKIPPED";
			break;
		}

		Reporter.log("Test Status: " + status, true);
	}

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

}
