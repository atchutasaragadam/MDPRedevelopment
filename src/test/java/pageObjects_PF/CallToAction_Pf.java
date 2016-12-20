package au.com.mazda.MDP.PageObjects_PF;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import au.com.mazda.MDP.TestCases.SuperClass;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class CallToAction_Pf extends SuperClass {

	@FindBy(how = How.CSS, using = "div[data-test='button']")
	public List<WebElement> LOC_LIST_CTA_BTN;

	public CallToAction_Pf(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyCtaBtn() throws Exception {
		try {
			getCountofElements(LOC_LIST_CTA_BTN, "CTA buttons");
			getAllElements(LOC_LIST_CTA_BTN, "CTA buttons");
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}
	}

}
