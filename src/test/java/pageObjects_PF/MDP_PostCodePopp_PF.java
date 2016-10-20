package pageObjects_PF;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestException;

import com.relevantcodes.extentreports.LogStatus;

import testCases.MDP_SuperClass;
import utility.MDP_MyCustomisedLogs;

public class MDP_PostCodePopp_PF extends MDP_SuperClass {

	@CacheLookup
	@FindBy(how = How.CSS, using = "svg[data-test='postcode']")
	public static WebElement we_PostCodeIcon;

	@FindBy(how = How.CSS, using = "input[data-test='Enter Postcode']")
	public WebElement we_Loc_EnterPostCodeText;

	@FindBy(how = How.CSS, using = "button[data-test='Submit']")
	public WebElement we_btn_SubmitBtn;

	@FindBy(how = How.CSS, using = "div[data-test='close']")
	public WebElement we_btn_CancelBtn;

	@FindBy(how = How.CSS, using = "div[data-test='contentpostcode']")
	public WebElement we_content;

	public MDP_PostCodePopp_PF(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// ***** main execution start for post code pop up ***** //
	public void execute_PostCode_PopUP() throws Exception {
		try {
			// allElementsList(we_List_allElements);
			allElement_List(we_content);

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	// ***** post code pop up - submit button ****** //
	public void execute_PostCodeUP_Submit(String sPostCode) throws Exception {
		try {

			System.out.println("To test submit button  on post code pop up");

			clickOnPostCodeIcon(we_PostCodeIcon);
			enterPostCode(we_Loc_EnterPostCodeText, sPostCode);
			clickOnSubmitButton(we_btn_SubmitBtn);
			getElementsText(we_PostCodeIcon, sPostCode);

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	// post code - pop up - Close button
	public void execute_PostCodeUP_Close() throws Exception {

		try {

			System.out.println("To test close button  on post code pop up");

			clickOnPostCodeIcon(we_PostCodeIcon);
			enterPostCode(we_Loc_EnterPostCodeText, "3000");
			clickOnCloseButton(we_btn_CancelBtn);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	public void execute_getCookiePostCode() throws Exception {
		try {
			System.out.println("To test 'Get the cookie for the post code");
			we_PostCodeIcon.getText();
			System.out.println("");
			clickOnPostCodeIcon(we_PostCodeIcon);
			// enterPostCode(we_Loc_EnterPostCodeText, "3000");
			// clickOnSubmitButton(we_btn_SubmitBtn);
			postCodeCookie(we_PostCodeIcon);

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}
	}

	private void getElementsText(WebElement element, String postcode) {
		try {
			element.getText();
			if (element.getText() == postcode) {
				MDP_MyCustomisedLogs.info("Location is set to this post code : " + postcode);
				test.log(LogStatus.INFO, "Location is set to this post code : " + postcode);

			} else {

				MDP_MyCustomisedLogs.info("Location is not set to correct post code : " + postcode);
				test.log(LogStatus.INFO, "Location is not set to correct post code : " + postcode);

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	// retrieves all the elements present in the post code pop up
	private void allElementsList(List<WebElement> elements) {
		try {
			for (WebElement we : elements) {

				MDP_MyCustomisedLogs.info("Elements are : <  " + we.getText() + "  >");
				test.log(LogStatus.INFO, "Elements are : <  " + we.getText() + "  >");

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}
	}

	// retrieves all the elements present in the post code pop up
	private void allElement_List(WebElement element) {
		try {
			// for (WebElement we : elements) {
			MDP_MyCustomisedLogs.info("Element get attribute are : <  " + element.getAttribute("data-test") + "  >");
			test.log(LogStatus.INFO, "Element get attribute are : <  " + element.getAttribute("data-test") + "  >");

			MDP_MyCustomisedLogs.info("Elements are : <  " + element.getText() + "  >");
			test.log(LogStatus.INFO, "Elements are : <  " + element.getText() + "  >");

			// }
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}
	}

	private void clickOnPostCodeIcon(WebElement element) {
		try {
			element.click();
			MDP_MyCustomisedLogs.info("Clicked on Post Code icon on the footer");
			test.log(LogStatus.INFO, "Clicked on Post Code icon on the footer");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	private void clickOnSubmitButton(WebElement element) throws Exception {
		try {
			Thread.sleep(1500);
			element.click();
			MDP_MyCustomisedLogs.info("Clicked on Submit button post code pop up >");
			test.log(LogStatus.INFO, "Clicked on Submit button post code pop up >");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	private void clickOnCloseButton(WebElement element) throws Exception {
		try {
			// Thread.sleep(3000);
			element.click();
			MDP_MyCustomisedLogs.info("Clicked on Cancel button post code pop up >");
			test.log(LogStatus.INFO, "Clicked on Cancel button post code pop up >");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	private void enterPostCode(WebElement element, String postcode) throws Exception {
		try {

			element.clear();
			element.sendKeys(postcode);
			MDP_MyCustomisedLogs.info("entered post code : " + postcode);
			test.log(LogStatus.INFO, "entered post code : " + postcode);
			System.out.println("entered post code : " + postcode);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}

	}

	private void postCodeCookie(WebElement element) throws Exception {
		try {

			Set<org.openqa.selenium.Cookie> cookies = driver.manage().getCookies();
			System.out.println("Size: " + cookies.size());

			Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
			while (itr.hasNext()) {
				org.openqa.selenium.Cookie cookie = itr.next();
				System.out.println(cookie.getName() + "::" + cookie.getPath() + "<" + cookie.getDomain() + "> ||"
						+ cookie.getValue());

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());

			throw new TestException(e.toString());
		}
	}
}
