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
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;


public class BrowserDriver extends Base{

	static WebDriver driver;
	
	public static void getDriver(String portnumber, String browserName,String baseurl)
	{
		Wrapper wa = Wrapper.getInstance();
		CustomLogger.start("browser ::-->>");

		try {
			if(browserName.equalsIgnoreCase("firefox"))
			{
				String nodeURL = "http://172.29.69.173:4547";
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\geckodriver.exe");
				DesiredCapabilities caps = new DesiredCapabilities().firefox();
				caps.setBrowserName("firefox");
				caps.setPlatform(Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL(nodeURL),caps);
			}
			else if(browserName.equalsIgnoreCase("chrome") && portnumber=="4546")
			{
				String nodeURL = "http://172.29.69.173:4546";
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\chromedriver.exe");
				
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "latest");
				caps.setCapability("browserstack.local", "false");
				caps.setCapability("browserstack.selenium_version", "3.14.0");
				
				driver = new RemoteWebDriver(new URL(nodeURL),caps);
	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
