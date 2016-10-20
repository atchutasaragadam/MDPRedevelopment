package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleMDPTests {
	
	public static WebDriver driver1;
	
//	@FindBy(className="cta-state-1")
//	public static WebElement cta_Mazda3;
	

	public static void main(String[] args) throws Exception {
		
	driver1 = new ChromeDriver();
	driver1.get("https://www.mazdausa.com/");
	
	Thread.sleep(7000);
	driver1.findElement(By.cssSelector("body > main > div.hero > div > div > div.mde-hero__cta-container > a:nth-child(1) > div > span > span.cta-state-1")).isDisplayed();
	driver1.findElement(By.cssSelector("body > main > div.hero > div > div > div.mde-hero__cta-container > a:nth-child(1) > div > span > span.cta-state-1")).click();
	String str_CurrentURL = driver1.getCurrentUrl();
	System.out.println("str_CurrentURL : " +str_CurrentURL);
		

	}

}
