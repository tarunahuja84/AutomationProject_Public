package com.Grid.Core;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserDriver extends Base{

	static WebDriver driver;
	static String hubIP="http://192.168.1.0";
	
	public static void getDriver(String browserName,String baseurl)
	{
		Wrapper wa = Wrapper.getInstance();
		CustomLogger.start("browser");

		try {
			if(browserName.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\geckodriver.exe");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("hostIP",hubIP);
				cap.setCapability("Port","4444");
				cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
				cap.setCapability("OS NAMe", Platform.WINDOWS);
				FirefoxOptions fOpt = new FirefoxOptions();
				fOpt.merge(cap);
				
				driver = new FirefoxDriver(fOpt);

			}
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\chromedriver.exe");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("hostIP",hubIP);
				cap.setCapability("Port","4444");
				cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
				cap.setCapability("OS NAMe", Platform.WINDOWS);
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
				
				driver = new ChromeDriver(options);		
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
