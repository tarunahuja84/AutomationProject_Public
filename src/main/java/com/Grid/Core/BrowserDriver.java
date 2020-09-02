package com.Grid.Core;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;


public class BrowserDriver extends Base{

	static WebDriver driver;
	
	public static void getDriver(int portnumber, String browserName,String baseurl)
	{
		Wrapper wa = Wrapper.getInstance();
		CustomLogger.start("browser ::-->>");

		try {
			
			  if(browserName.equalsIgnoreCase("firefox") && portnumber==4547){ 
			  
		      String nodeURL ="http://172.29.69.173:4547/wd/hub"; 
			  System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\BrowserDriver\\geckodriver.exe");
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
				caps.setCapability("browser", "firefox");
				//test
				FirefoxOptions options = new FirefoxOptions();
				options.merge(caps);			  
			  driver =new RemoteWebDriver(new URL(nodeURL),options); 
			  } 
			  else if(browserName.equalsIgnoreCase("chrome") && portnumber==5056)
			{
				System.out.println("Before NODE URL IS MADE");
				 String nodeURL = "http://172.29.69.173:5056/wd/hub";
				System.out.println("SETTING UP THE DESIRED CAPABILITIES");
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
				caps.setCapability("browser", "Chrome");
				ChromeOptions options = new ChromeOptions();
				options.merge(caps);
				System.out.println("bEFORE HITTING HUB");
				driver = new RemoteWebDriver(new URL(nodeURL),options);
				System.out.println("AFTER HITTING HUB");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("********************************INSIDE CATCH********************************");
			e.printStackTrace();
		}
		System.out.println("GETTING BASE URL ----->>> " + baseurl);
		driver.get(baseurl);
		wa.setWebDriver(driver);
	}
	
	public static void StopDriver() {
		Wrapper WA =Wrapper.getInstance();

		driver = WA.getWebDriver();
		try {
			if (driver != null) {
				driver.close();
				driver.quit();
				driver = null;
				CustomLogger.Log.info("Closing the driver" + "Driver quit");
			}
		} catch (Exception ignore) {
			System.out.println("Getting Exception while closing the driver: "
					+ ignore);
			CustomLogger.Log.info("Closing the driver "
					+ "Getting Exception while closing the driver");
		}
		}	
	
}
