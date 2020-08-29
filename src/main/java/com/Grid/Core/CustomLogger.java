package com.Grid.Core;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CustomLogger {

	public static Logger Log;

	/*public static void logInfo(String ClassName, String MessageInLogs) {
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("*************************"+ClassName);
		Log = Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
		Log.info(MessageInLogs);
	}*/
	
	public static void start(String messgae) {
		PropertyConfigurator.configure("Log4j.properties");
		Log = Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
		Log.info(messgae);
		//Log.info(">>>>>>>>>>>>>>>>>>>>>" + TestCaseName+ "<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName) {
		Log.info("-----------------------" + "-E---N---D-"+ "-----------------------");
	}
}
