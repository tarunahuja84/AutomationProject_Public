package com.pages;

import org.openqa.selenium.By;

import com.Grid.Core.Wrapper;

public class GoogleSearchPage {

	
	By searchMenu = By.xpath("//input[@name='q']");
	By search_button = By.xpath("//input[@class='gNO89b']");
	
	private static GoogleSearchPage ahp =null;
	Wrapper WA = Wrapper.getInstance();
	
	public static GoogleSearchPage getInstance()
	{
		if (ahp == null) {
			ahp = new GoogleSearchPage();
		}
		return ahp;
	}

	public void searchKeyword(String keyword){
		
		WA.setTextBoxValue(searchMenu, keyword);
		WA.click(search_button);
		WA.waitForBrowserToLoadCompletely();
	}
}