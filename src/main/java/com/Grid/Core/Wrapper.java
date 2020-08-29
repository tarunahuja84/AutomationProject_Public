package com.Grid.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Wrapper {


	private static Wrapper baseobj;
	public static WebDriver driver = null;
	WebElement element;
	public  String  xmlelementvalue = null;

	
	public static Wrapper getInstance() {
		if(baseobj==null) {
			baseobj = new Wrapper();
		}
		return baseobj;
	}

	
	public void setWebDriver(WebDriver webdriver)
	{
		Wrapper.driver=webdriver;
	}
	
	public WebDriver getWebDriver()
	{
		return Wrapper.driver;
	}
	

	
		
	public void settDriver(WebDriver driver) {
		Wrapper.driver = driver;
	}
	
	public WebDriver getDriver(){
		return Wrapper.driver;
	}

	
	
	public String getAttribute_ButtonName(By locator, String attribute) {
		element=getElement(locator);
		System.out.println("Value of attribute" +  attribute + " is: "+element.getAttribute(attribute));
		return element.getAttribute(attribute);
	}
	
	
	//---------------------------------- Start Text Box-----------------------------------
	public String getTextBoxValue(By locator) {
		this.element = getElement(locator);
		return element.getAttribute("value");
	}

	public void setTextBoxValue(By locator, String text) {
		this.element = getElement(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	
	public void sendkeys(By locator)
	{
		this.element = getElement(locator);
		element.sendKeys(Keys.ENTER);
	}
	
	
//	public static String xmlReader(String NodeName, String PropertyValue) {
//        Element element = null;
//        String Value = null;
//        try {
//
//               File Prop = new File("TestData/DataPropeties.xml");
//               DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//               DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
//               Document doc = dbBuilder.parse(Prop);
//               doc.getDocumentElement().normalize();
//               doc.getDocumentElement().getNodeName();
//               NodeList nodes = doc.getElementsByTagName(NodeName);
//
//               for (int i = 0; i < nodes.getLength(); i++) {
//                     Node node = nodes.item(i);
//                     if (node.getNodeType() == Node.ELEMENT_NODE) {
//                            element = (Element) node;
//                     }
//               }
//            //   Value = getValue(PropertyValue, element);
//        } catch (Exception ex) {
//               ex.printStackTrace();
//        }
//        return Value;
// }

	public String readXML(String env, String element)
	 {
	  try {
	   
	   File fXmlFile = new File(System.getProperty("user.dir")+"/src/test/resources/resource/testdata.xml");
	   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	   Document doc = dBuilder.parse(fXmlFile);
	   doc.getDocumentElement().normalize();
	   NodeList nList = doc.getElementsByTagName(env);
	   for (int temp = 0; temp < nList.getLength(); temp++) {

	    Node nNode = nList.item(temp);
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	     Element eElement = (Element) nNode;
	     xmlelementvalue = eElement.getElementsByTagName(element).item(0).getTextContent();
	    }
	   }
	  } catch (DOMException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (ParserConfigurationException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (SAXException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  return xmlelementvalue;
	  
	   
	  
	 }

	public boolean isReadOnlyTextBox(By locator) {
		this.element = getElement(locator);
		if ((element.getAttribute("readonly") != null) || (element.getAttribute("disabled") != null)) {
			return true;
		} else {
			return false;
		}
	}
	//---------------------------------- End Text Box-----------------------------------

	public WebElement getElement(By locator) {
		try {
			int count = 1;
			System.out.println("Waiting upto " + 60000 + "ms for element with locator: \"" + locator + "\" to appear on page.");
			/*while (driver.findElements(locator).size()==0 && count<=60) {
				Thread.sleep(1000);
				System.out.println("Waiting " + 1 + "000 ms for count "+ count);
				count++;
			}*/
			return (driver.findElement(locator));
		} catch (NoSuchElementException e) {
			
			Assert.fail(locator + " NoSuchElementException NOT FOUND. TEST FAILED.");
		} catch (NullPointerException e) {
			Assert.fail(locator + "NullPointerException NOT FOUND. TEST FAILED.");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
		}
		return null;
	}
	
	
	//---------------------------------- Start Drop Down-----------------------------------
	private Select select(By locator){
		Select selectElement = null;
		this.element=getElement(locator);
		try{
			selectElement = new Select(element);
		}catch(UnexpectedTagNameException e){
			Assert.fail("Element " + locator + " was not in select tag name   Error Message UnexpectedTagNameException  -->" + e.getStackTrace());
		}
		return selectElement;
	}
	
	public void deselectAll(By locator) {
		select(locator).deselectAll();
	}
	
	public void deselectByIndex(By locator, int index) {
		select(locator).deselectByIndex(index);
	}
	
	public void deselectByValue(By locator, String value) {
		select(locator).deselectByValue(value);
	}
	
	public void deselectByVisibleText(By locator, String text) {
		select(locator).deselectByVisibleText(text);
	}
	
	public List<WebElement> getAllSelectedOptions(By locator) {
		return (select(locator).getAllSelectedOptions());
	}
	
	public WebElement getFirstSelectedOption(By locator) {
		return select(locator).getFirstSelectedOption();
	}

	//All options belonging to this select tag
	public List<WebElement> getOptions(By locator) {
		return select(locator).getOptions();
	}
	
	public boolean isMultiple(By locator) {
		return select(locator).isMultiple();
	}
	
	//index start from 0 to n-1
	public void selectByIndex(By locator, int index) {
		select(locator).selectByIndex(index);
	}
	
	public void selectByValue(By locator, String value) {
		select(locator).selectByValue(value);
	}
	
	public void selectByVisibleText(By locator, String text) {
		select(locator).selectByVisibleText(text);
	}
	
	
	//---------------------------------- End Drop Down-----------------------------------
	
	
	public void browseURL(String url) {
		driver.get(url);
	}
	
	public String  currentPageURL() {
		System.out.println(driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	public void navigate(String url){
		driver.navigate().to(url);
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public void maximizeWindowwithGivenDimension(int width, int height){
		driver.manage().window().setSize(new Dimension(width,height));
	}
	
	
	public WebDriver openFirefoxBrowser(){
		return (Wrapper.driver = new FirefoxDriver());
	}
	
	/*
	 * public WebDriver openFirefoxBrowserWithProfile(String profileName){
	 * ProfilesIni profile = new ProfilesIni(); FirefoxProfile myprofile =
	 * profile.getProfile(profileName); return (Wrapper.driver = new
	 * FirefoxDriver()); }
	 */	
	public WebDriver openChromeBrowser(){
		
		return (driver);
	}
	
	public WebDriver openIEBrowser(){
		return (driver);
	}
	
	
	
	
	
	
	
	//---------------------------------- Start Click button-----------------------------------
	public void click(By locator){
		this.element=getElement(locator);
		element.click();
		baseobj.holdon(2);

	}
	
	
	
	
	public void doubleClick(By locator) {
		this.element=getElement(locator);
		Actions action = new Actions(driver).doubleClick(element);
		action.build().perform();
	}
	
	//---------------------------------- Start Click button----------------------------------
	
	//---------------------------------- Start table-----------------------------------
	public void table(By locator) {
		WebElement htmltable = getElement(locator);
		List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
		for (int rnum = 0; rnum < rows.size(); rnum++) {
			List<WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));
			for (int cnum = 0; cnum < columns.size(); cnum++) {
				
			}
		}

	}
	//---------------------------------- End Table----------------------------------
	public void switchWindow(){
		String mainWindow = driver.getWindowHandle();
		Set<String> window = Wrapper.getInstance().getDriver().getWindowHandles();
		for(String win:window){
			if(!(win.equalsIgnoreCase(mainWindow))){
				driver.switchTo().window(win);
			}
		}
	
	}
	
	
	
	//---------------------------------- Start Close browser -------------------
	public void closeCurrentWindow(){
		driver.close();
	}
	
	public void closeAllWindow(){
		driver.quit();
	}
	
	public void closeChromeInstance(){
		try
		{
		    Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
		    Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
		} catch (IOException e)
		{
		    e.printStackTrace();
		}
	}
	//---------------------------------- End Close browser -------------------
	
	
	
	public void refresh(){
		driver.navigate().refresh();
	}
	
	public void refreshByPhysicalKey(){
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}
	
	public void pressEscKey(){
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.ESCAPE).perform();
	}
	
	public void forward(){
		driver.navigate().forward();
	}
	
	public void back(){
		driver.navigate().back();
	}
	
	
	
	//------------------------------- Start Cookie---------------------------
	
	public void addCookie(String cookieName, String cookieValue){
		Cookie name = new Cookie(cookieName,cookieValue);
		driver.manage().addCookie(name);
	}
	
	
	public void deleteCookieByName(String cookieName){
		driver.manage().deleteCookieNamed(cookieName);
	}
	
	public void deleteAllCookies(){
		driver.manage().deleteAllCookies();
	}
	
	public Cookie getCookieByName(String cookieName){
		return (driver.manage().getCookieNamed(cookieName));
	}
	
	public Set<Cookie> getAllCookies(){
		return (driver.manage().getCookies());
	}
	
	//------------------------------- End Cookie ----------------------------
	
	
	
	
	
	
	public String readPropertyFile(File file, String key){
		Properties prop = new Properties();
		InputStream input = null;
		String result;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		// get the property value and print it out
		result=prop.getProperty(key);
		try {
			input.close();
		} catch (Exception e) {
		}
		
		return result;
		
	}
	
	
	
	
	 
	public List<WebElement> getAnchorTagsList(){
		return (driver.findElements(By.tagName("a")));
	}
	
	public void waitForBrowserToLoadCompletely(){
		String state=null;
		String oldstate=null;
		try{
			System.out.print("Waiting for browser loading to complete");
			int i=0;
			while(i<5){	
				Thread.sleep(1000);
				state= ((JavascriptExecutor)driver).executeScript("return document.readyState;").toString();				
				System.out.print("."+Character.toUpperCase(state.charAt(0))+".");
				if(state.equals("interactive")||state.equals("loading"))
					break;				
				/*
				 * If browser in 'complete' state since last X seconds.
				 * Return.	
				 */

				if(i==1 && state.equals("complete")){
					System.out.println();
					return;
				}				
				i++;
			}
			i=0;
			oldstate=null;
			Thread.sleep(2000);

			/*
			 * Now wait for state to become complete
			 */
			while(true){			
				state= ((JavascriptExecutor)driver).executeScript("return document.readyState;").toString();				
				System.out.print("."+state.charAt(0)+".");
				if(state.equals("complete"))
					break;

				if(state.equals(oldstate))
					i++;
				else
					i=0;
				/*
				 * If browser state is same (loading/interactive) since last 60 secs. Refresh the page.
				 */
				if(i==15&&state.equals("loading")){
					System.out.println("\nBrowser in "+state+" state since last 60 secs. So refreshing browser.");
					driver.navigate().refresh();
					System.out.print("Waiting for browser loading to complete");
					i=0;
				}else if(i==6&&state.equals("interactive")){
					System.out.println("\nBrowser in "+state+" state since last 30 secs. So starting with execution.");
					return;
				}

				Thread.sleep(4000);
				oldstate=state;

			}
			System.out.println();

		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
	
	public void holdon(int time){
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//------------------------------- Start Scrolling---------------------------
	public void scrollingToBottomofAPageUsingJS() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	
	public void scrollingToBottomofAPageUsingAction(){
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	}

	public void scrollingToElementofAPage(By locator) {
		this.element=getElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void scrollingByCoordinatesofAPage(int xAxis, int yAxis) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy("+xAxis+","+yAxis+")");
	}
	
	//------------------------------- End Scrolling---------------------------
	
	
	
	
	public void dragAndDrop(By sourceLocator, By destinationLocator)
    {
        (new Actions(driver)).dragAndDrop(getElement(sourceLocator), getElement(destinationLocator)).perform();
    }
	
	
	
	
	//------------------------------- Start Alert---------------------------
	
	public void checkIsAlertPresent(){
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		if(wait.until(ExpectedConditions.alertIsPresent())==null){
		    Assert.fail("alert was not present");
		}else {
			System.out.println("Alert is present");
		}
		
	}
	
	public void clickCheckBox(By locator){
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void alertAccept() {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void alertDismiss() {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public String getLabelText(By locator) {

		return driver.findElement(locator).getText().toString();
	}
	
	public void alertSendKeys(String value) {
		checkIsAlertPresent();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}
	
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	
	//------------------------------- End Alert ----------------------------
		
		
	
		
	
	//------------------------------- Start Date ----------------------------
	public int getCurrentDate(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.DATE));
	}
	
	public int getCurrentMonth(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.MONTH));
	}
	
	public int getCurrentYear(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.YEAR));
	}
	
	//------------------------------- End Date ----------------------------

	//------------------------------- Start Screen Short ----------------------------
	 public void getscreenshot() throws Exception 
     {
             File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          //The below method will save the screen shot in d drive with name "screenshot.png"
             FileUtils.copyFile(scrFile, new File("D:\\aaaaaaaaaaaaa.png"));
     }
	//------------------------------- End Screen Shot ----------------------------
	 
	public boolean getCheckboxIsSelected(By locator) {
		System.out.println("Is checkbox selected: "+driver.findElement(locator).isSelected());
		return driver.findElement(locator).isSelected();
	}
	
	

	public  String randomUser() {
		String[] name = { "qa", "ql", "phpcntrl", "pch", "user",
				"accounts" };

		Random ran = new Random();

		String randomuser = name[ran.nextInt(name.length)] + " "
				+ name[ran.nextInt(name.length)];

		return randomuser;

	}
	public String epochTime()

	{
		long epoch = System.currentTimeMillis();
		String strLong = Long.toString(epoch / 1000);
		return strLong;
	}
	
	
	
	
}
