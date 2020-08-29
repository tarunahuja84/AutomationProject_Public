package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import com.Grid.Core.Base;
import com.Grid.Core.Wrapper;
import com.pages.AmazonHomePage;
import com.pages.GoogleSearchPage;


public class GoogleSearchTest extends Base {

	GoogleSearchPage GS = GoogleSearchPage.getInstance();
	Wrapper baseobj = Wrapper.getInstance();
	
	@Test(description = "Verify that user is able to Search Kitchen Set on Google sucessfully.")
	@Tag(name = "Regression")
	public void performGoogleSearch() {
		test = extent.startTest("Verify that user is able to Search Kitchen Set on Google sucessfully").assignCategory("Google Advance Search");
		GS.searchKeyword("playing kitchen set");
		baseobj.holdon(5);
		Assert.assertTrue(baseobj.currentPageURL().contains("/advancedsearch"));
	}
	
	
}
