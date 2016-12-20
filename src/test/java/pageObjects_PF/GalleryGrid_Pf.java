package au.com.mazda.MDP.PageObjects_PF;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import au.com.mazda.MDP.TestCases.SuperClass;
import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;

public class GalleryGrid_Pf extends SuperClass {

	@FindBy(how = How.CSS, using = "span[data-test='in-page-navigation-item']")
	public List<WebElement> LOC_INPAGE_NAV_LIST;

	@FindBy(how = How.CSS, using = "div[data-test='heading']")
	public WebElement LOC_GALLERY_HEADING;

	@FindBy(how = How.CSS, using = "figure[data-test='gallery-item']")
	public List<WebElement> LOC_GALLERY_IMG_LIST;

	@FindBy(how = How.CSS, using = "video")
	public List<WebElement> LOC_GALLERY_VIDEO_LIST;

	@FindBy(how = How.CSS, using = "div[data-test='overlay']")
	public WebElement LOC_GALLERY_IMG_OVERLAY;

	@FindBy(how = How.CSS, using = "div[data-test='overlay-close']")
	public WebElement LOC_GALLERY_IMG_OVERLAYCLOSE;

	@FindBy(how = How.CSS, using = "section[data-test='fifty-fifty-block']")
	public WebElement LOC_FIFTYFIFTYBLOCK_IMG;

	// @FindBy(how = How.XPATH, using =
	// "//span[data-test='in-page-navigation-item']")
	// public List<WebElement> LOC_INPAGE_NAV_LIST;
	//
	// @FindBy(how = How.XPATH, using = "//div[data-test='heading']")
	// public WebElement LOC_GALLERY_HEADING;
	//
	// @FindBy(how = How.XPATH, using = "//figure[data-test='gallery-item']")
	// public List<WebElement> LOC_GALLERY_IMG;
	//
	// @FindBy(how = How.XPATH, using = "//video")
	// public List<WebElement> LOC_GALLERY_VIDEO;
	//
	// @FindBy(how = How.XPATH, using = "//div[data-test='overlay']")
	// public WebElement LOC_GALLERY_IMG_OVERLAY;
	//
	// @FindBy(how = How.XPATH, using = "//div[data-test='overlay-close']")
	// public WebElement LOC_GALLERY_IMG_OVERLAYCLOSE;
	//
	// @FindBy(how = How.XPATH, using =
	// "//section[data-test='fifty-fifty-block']")
	// public WebElement LOC_FIFTYFIFTYBLOCK_IMG;
	// SeleniumActions selAct;

	// constructor with driver as the parameter, to invoke the same driver when
	// executing the tests
	public GalleryGrid_Pf(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyGalleryVideos() throws Exception {
		try {
			int count = getCountofElements(LOC_GALLERY_VIDEO_LIST,
					"Videos  in Gallery Grid");
			for (int i = 0; i <= count; i++) {
				LOC_GALLERY_IMG_LIST.get(i).click();

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	public void verifyGalleryImgs() throws Exception {
		try {
			int count = getCountofElements(LOC_GALLERY_IMG_LIST,
					"Images  in Gallery Grid");
			for (int i = 0; i <= count; i++) {
				LOC_GALLERY_IMG_LIST.get(i).click();
				isElementPresent(LOC_GALLERY_IMG_OVERLAY);
				isElementPresent(LOC_FIFTYFIFTYBLOCK_IMG);
				isElementPresent(LOC_GALLERY_HEADING);
				clickOnElement(LOC_GALLERY_IMG_OVERLAYCLOSE);

			}
		} catch (Exception e) {
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

	public void verifyGalleryInNavPage() throws Exception {

		try {

			// getCountofElements(LOC_INPAGE_NAV_LIST,
			// "Navigation List in Gallery Grid");
			// getAllElements(LOC_INPAGE_NAV_LIST);
			// selectThisElement("Gallery", LOC_INPAGE_NAV_LIST);
			isElementPresent(LOC_GALLERY_HEADING);
			getTextofElement(LOC_GALLERY_HEADING);

			getCountofElements(LOC_GALLERY_VIDEO_LIST,
					"Videos in Gallery Grid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MDP_MyCustomisedLogs.error(e.toString());
			throw new Exception(e.toString());
		}

	}

}
