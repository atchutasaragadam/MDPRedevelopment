package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HTMLUnitDriver_Test {
	
	public static void main(String[] args) {
		WebDriver driverHTML = new HtmlUnitDriver();
		
		//driverHTML.get("https://www.mazdausa.com/");
		driverHTML.get("http://koala.node.chdigital.com.au/");
		String PgSrc = driverHTML.getPageSource();
		
		System.out.println("PgSrc : " +PgSrc);
		
	}

}
