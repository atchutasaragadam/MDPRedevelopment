package pageObjects_PF;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestException;

import com.relevantcodes.extentreports.LogStatus;

import testCases.MDP_SuperClass;
import utility.MDP_MyCustomisedLogs;

public class MDP_Menu_PF extends MDP_SuperClass {

	@FindBy(how = How.CSS, using = "span[data-test='Our Range']")
	public WebElement we_Menu_OurRange;

	@FindBy(how = How.CSS, using = "span[data-test='Buying Guide']")
	public WebElement we_Menu_BuyingGuide;

	@FindBy(how = How.CSS, using = "a[data-test='Find a Dealer']")
	public WebElement we_Menu_FindADealer;

	@FindBy(css = "button[data-test='More +']")
	public WebElement we_Menu_More;

	@FindBy(how = How.CSS, using = "span")
	public List<WebElement> we_List_AllSpanElements;

	@FindBy(how = How.TAG_NAME, using = "a")
	public List<WebElement> we_list_AllTags_A_Elements;

	public MDP_Menu_PF(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public void execute_Menu() throws Exception {
		try {

		// #Menu - Our Range
		isElementDisplayed(we_Menu_OurRange);
		isElementClickable(we_Menu_OurRange);

		// # Menu - Buying Guide
		isElementDisplayed(we_Menu_BuyingGuide);
		isElementClickable(we_Menu_BuyingGuide);

		// #Menu - Find A Dealer
		isElementDisplayed(we_Menu_FindADealer);
		isElementClickable(we_Menu_FindADealer);

		// #Menu - More +
		isElementDisplayed(we_Menu_More);
		isElementClickable(we_Menu_More);
		} catch(Exception e) {
			
			MDP_MyCustomisedLogs.error(e.toString());
			test.log(LogStatus.ERROR, e.toString());
			throw new TestException(e.toString());
		}

	}

	private void isElementDisplayed(WebElement element) {
		
		try {
			element.isDisplayed();
			MDP_MyCustomisedLogs.info("Element <=== " + element.getText() + " ===> is displayed on header");
			test.log(LogStatus.INFO, "Element <=== " + element.getText() + " ==> is displayed on header");
		} catch (Exception e) {
		
			MDP_MyCustomisedLogs.error("Element <=== " + element.getText() + " ===> is not displayed" +e.toString());
			test.log(LogStatus.ERROR, "Element <=== " + element.getText() + " ==> is not displayed" +e.toString());
			throw new TestException("Element <=== " + element.getText() + " ==> is not displayed" +e.toString());
		}

		
	}

	private void isElementClickable(WebElement element) {
		
		try {
			element.click();
			MDP_MyCustomisedLogs.info("Clicked on  < " + element.getText() + "   ===>");
			test.log(LogStatus.INFO, "Clicked on  < " + element.getText() + "   ===>");
			
		} catch (Exception e) {
		
		MDP_MyCustomisedLogs.error("Unable to click on  < " + element.getText() + "   ===>" +e.toString());
		test.log(LogStatus.ERROR, "Unable to click on  < " + element.getText() + "   ===>" +e.toString());
		throw new TestException("Unable to click on  < " + element.getText() + "   ===>" +e.toString());
		}
	}
	
	}
