package com.pages;

import org.openqa.selenium.By;

import com.Grid.Core.Wrapper;

public class AmazonHomePage {

	
	By searchMenu = By.id("//input[@id='twotabsearchtextbox']");
	By search_button = By.xpath("(//input[@type='submit'])[1]");
	
	
	private static AmazonHomePage ahp =null;
	Wrapper WA = Wrapper.getInstance();
	
	public static AmazonHomePage getInstance()
	{
		if (ahp == null) {
			ahp = new AmazonHomePage();
		}
		return ahp;
	}

	public void searchKeyword(String keyword){
		
		WA.setTextBoxValue(searchMenu, keyword);
		WA.click(search_button);
		WA.waitForBrowserToLoadCompletely();
	}
}