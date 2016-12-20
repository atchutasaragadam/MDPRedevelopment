/// **
// * method present are
// * 1. isElementPresent()
// * 2. getText()
// * 3. sendValues()
// * getCountofElements
// * getAllElements
// *
// */
// package au.com.mazda.MDP.PageObjects_PF;
//
// import java.util.Iterator;
// import java.util.List;
// import java.util.Set;
//
// import org.openqa.selenium.WebElement;
//
// import au.com.mazda.MDP.TestCases.SuperClass;
// import au.com.mazda.MDP.Utility.MDP_MyCustomisedLogs;
//
/// **
// * @author cheaus
// *
// */
// public class SeleniumActions extends SuperClass {
//
// /**
// * this class will have all generic operations
// */
//
// // verifies whether the element is present or not
// public void isElementPresent(WebElement element) {
// try {
// if (element.isDisplayed()) {
// MDP_MyCustomisedLogs
// .info(element.getText() + "is present in the webpage");
//
// }
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error(element.getText()
// + "is not present in the webpage" + e.toString());
// }
//
// }
//
// // retrieves the text of the element
// public String getTextofElement(WebElement element) {
// try {
// MDP_MyCustomisedLogs
// .info(element.getText() + "is present in the webpage");
// return element.getText();
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error(element.getText()
// + "is not present in the webpage" + e.toString());
// return "";
// }
//
// }
//
// // send input values to text field
// public void sendValues(WebElement element, String sendText) {
// try {
// element.clear();
// element.sendKeys(sendText);
// MDP_MyCustomisedLogs.info("Value entered in inputfield < "
// + element.getText() + "> is < " + sendText + " > ");
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error("unable to enter text < " + sendText
// + " > in the element < " + element.getText() + " > "
// + e.toString());
// }
//
// }
//
// // get the count of elements
// public int getCountofElements(List<WebElement> elements, String string) {
// try {
//
// MDP_MyCustomisedLogs.info(
// "Count in < " + string + " > is " + elements.size());
// return elements.size();
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error(e.toString());
//
// return 0;
// }
//
// }
//
// // retrieve all the elements present in the webelements
// public String getAllElements(List<WebElement> elements) throws Exception {
// try {
// for (WebElement ele : elements) {
// return ele.getText();
// }
//
// } catch (Exception e) {
// MDP_MyCustomisedLogs
// .error("Unable to retrieve the element " + e.toString());
//
// }
// return null;
// }
//
// // click on an element
// public void clickOnElement(WebElement element) throws Exception {
// try {
// element.click();
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error("Unable to click on the element < "
// + element.getText() + " > " + e.toString());
//
// }
//
// }
//
// // gets the cookie
// private void getCookie(WebElement element) {
// try {
//
// Set<org.openqa.selenium.Cookie> cookies = driver.manage()
// .getCookies();
// System.out.println("Size: " + cookies.size());
//
// Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
// while (itr.hasNext()) {
// org.openqa.selenium.Cookie cookie = itr.next();
// System.out.println(cookie.getName() + "::" + cookie.getPath()
// + "<" + cookie.getDomain() + "> ||"
// + cookie.getValue());
//
// }
// } catch (Exception e) {
// MDP_MyCustomisedLogs.error(e.toString());
//
// }
// }
//
// public void selectThisElement(String string, List<WebElement> elements) {
// try {
//
// for (WebElement ele : elements) {
// if (string.equals(ele.getText())) {
// ele.click();
// }
// }
// } catch (Exception e) {
// MDP_MyCustomisedLogs
// .error(string + "is not present" + e.toString());
// }
//
// }
//
// }
