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
	
	public static void getDriver(String browserName,String baseurl)
	{
		Wrapper wa = Wrapper.getInstance();
		CustomLogger.start("browser ::-->>");

		try {
			if(browserName.equalsIgnoreCase("firefox"))
			{
				/*
				 * System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
				 * "\\BrowserDriver\\geckodriver.exe");
				 * 
				 */
			}
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\chromedriver.exe");
				
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setPlatform(Platform.WINDOWS);
				 driver = new RemoteWebDriver(new URL("http://192.168.0.24:4444/wd/hub"), cap);
	
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
