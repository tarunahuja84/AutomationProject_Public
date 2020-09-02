package com.Grid.Core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {

	protected static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setupReport()
	{
		extent = new ExtentReports("AutomationRunReport.html", true);
		extent.loadConfig(new File("extent-config.xml"));
		Map<String,String> sysInfo = new HashMap<String,String>();
		sysInfo.put("Selenium Verrsion","3.141.59");
		sysInfo.put("Environment","QA");
		sysInfo.put("Platform","Windows10Ten");
		extent.addSystemInfo(sysInfo);
	}
	
	@Parameters({"portnumber","browserName","baseurl"})
	@BeforeMethod
	public void initiateDriver(String portnumber, String browserName, String baseurl)
	{
		BrowserDriver.getDriver(portnumber,browserName, baseurl);
	}
	
	@AfterMethod
	 public static void StopDriver(ITestResult result) {
	  if (!result.isSuccess()) {
	   test.log(LogStatus.FAIL, result.getThrowable());
	   String methodName=result.getName().toString().trim();
	      takeScreenShot(methodName);
	  } else {
	   test.log(LogStatus.PASS, result.getMethod().getMethodName());
	  }

	  if (test != null) {
	   extent.endTest(test);
	  }
	     
	  BrowserDriver.StopDriver();
	  extent.flush();
	 }

	   public static void takeScreenShot(String methodName) {
	      //get the driver
	       File scrFile = ((TakesScreenshot)Wrapper.getInstance().getWebDriver()).getScreenshotAs(OutputType.FILE);
	          //The below method will save the screen shot in d drive with test method name 
	             try {
	     FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshot\\"+methodName+".png"));
	     System.out.println("***Placed screen shot ***");
	     test.log(LogStatus.FAIL, "Screencast below: " + test.addScreenCapture(System.getProperty("user.dir")+"\\screenshot\\"+methodName+".png"));
	    } catch (IOException e) {
	     e.printStackTrace();
	    }
	     }

	@AfterSuite
	protected void afterSuite() {
		extent.close();
	}

	
}
