package au.com.mazda.MDP.PageObjects_PF;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import au.com.mazda.MDP.TestCases.SuperClass;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

/**
 * Post Code Popup page factory
 * 
 * css selectors for elements and related behaviour.
 * 
 * 
 * @author Atchuta
 * @version 1.0
 */
public class PostCodePopp_PF extends SuperClass {

	@CacheLookup
	@FindBy(how = How.CSS, using = "span[data-test='postcode-selector']")
	public static WebElement LOC_POSTCODE_ICON;

	@FindBy(how = How.CSS, using = "div[data-reactroot data-test='toast-popup']")
	public List<WebElement> LOC_POSTCODE_POPUP;

	@FindBy(how = How.CSS, using = "div[data-test='input-postcode']")
	public WebElement LOC_INPUT_POSTCODE_TXT;

	@FindBy(how = How.CSS, using = "div[data-test='button']")
	public WebElement LOC_POSTCODE_SUBMIT_BTN;

	@FindBy(how = How.CSS, using = "div[data-test='contentpostcode']")
	public WebElement LOC_POSTODE_CLOSE;

	// SeleniumActions selAct;

	public PostCodePopp_PF(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// ***** main execution start for post code pop up ***** //
	public void execute_PostCodePopUP(String sPostCodeFromExcel) {
		try {

			// verifies the postcode icon present or not
			isElementPresent(LOC_POSTCODE_ICON);
			clickOnElement(LOC_POSTCODE_ICON); // clicks on post code
												// icon
			getAllElements(LOC_POSTCODE_POPUP, "PostCode Pop Up");
			sendValues(LOC_INPUT_POSTCODE_TXT, sPostCodeFromExcel);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

	// ***** main execution start for post code pop up ***** //
	public void execute_PostCodePopUPPresence() {

		try {
			// verifies the postcode icon present or not
			isElementPresent(LOC_POSTCODE_ICON);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());

		}

	}

	public void execute_getCookiePostCode() {

		try {
			MDP_MyCustomisedLogs
					.info("To test 'Get the cookie for the post code");
			execute_PostCodePopUP("3000");

			Set<org.openqa.selenium.Cookie> cookies = driver.manage()
					.getCookies();
			System.out.println("Size: " + cookies.size());

			Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
			while (itr.hasNext()) {
				org.openqa.selenium.Cookie cookie = itr.next();
				System.out.println(cookie.getName() + "::" + cookie.getPath()
						+ "<" + cookie.getDomain() + "> ||" + cookie.getValue()
						+ "::" + cookie.getExpiry());
			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}

	}

	// post code - pop up - Close button
	public void execute_PostCodeUP_Close() {

		try {
			MDP_MyCustomisedLogs
					.info("To test close button  on post code pop up");

			clickOnElement(LOC_POSTCODE_ICON);
			sendValues(LOC_INPUT_POSTCODE_TXT, "3000");
			clickOnElement(LOC_POSTODE_CLOSE);

		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}

	}

	// ***** post code pop up - submit button ****** //
	public void execute_PostCodeUP_Submit(String sPostCodeFromExcel) {
		try {

			System.out.println("To test submit button  on post code pop up");

			clickOnElement(LOC_POSTCODE_ICON);
			sendValues(LOC_INPUT_POSTCODE_TXT, sPostCodeFromExcel);
			clickOnElement(LOC_POSTCODE_SUBMIT_BTN);
			getTextofElement(LOC_POSTCODE_ICON);
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
		}
	}

}
