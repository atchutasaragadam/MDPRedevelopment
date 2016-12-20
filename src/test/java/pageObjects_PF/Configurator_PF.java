package au.com.mazda.MDP.PageObjects_PF;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import au.com.mazda.MDP.TestCases.SuperClass;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class Configurator_PF extends SuperClass {

	@FindBy(how = How.CSS, using = "div[data-test='heading']")
	public List<WebElement> WE_LIST_HEADING_BUILD;

	@FindBy(how = How.CSS, using = "li[data-test='flex-icon-link']")
	public List<WebElement> WE_LIST_SELECT_MODEL;

	@FindBy(how = How.TAG_NAME, using = "a")
	public List<WebElement> WE_LIST_MENU_NAV_LINK;

	public Configurator_PF(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public void execute_Configurator() throws Exception {

		verifyConfiguratorHeadersPresence();
		verifyModelCarsPresence();
		verifySelectModel();
	}

	/*
	 * gets the count of header elements and retireves text
	 */

	public void verifyConfiguratorHeadersPresence() throws Exception {

		int count = getCountofElements(WE_LIST_HEADING_BUILD,
				"Heading in Configurator");
		MDP_MyCustomisedLogs.info(
				"Count of Heading elements in Configurator are : " + count);

		getAllElements(WE_LIST_HEADING_BUILD, "Headers in Configurator");

	}

	public void verifyModelCarsPresence() throws Exception {

		int count = getCountofElements(WE_LIST_SELECT_MODEL,
				"Heading in Configurator");
		MDP_MyCustomisedLogs.info(
				"Count of Heading elements in Configurator are : " + count);

		getAllElements(WE_LIST_SELECT_MODEL, "Model Cars in configurator");

	}

	public void verifySelectModel() throws Exception {

		int count = getCountofElements(WE_LIST_SELECT_MODEL,
				"Heading in Configurator");
		MDP_MyCustomisedLogs.info(
				"Count of Heading elements in Configurator are : " + count);

		selectThisElement("Mazda2", WE_LIST_SELECT_MODEL);

	}

}
