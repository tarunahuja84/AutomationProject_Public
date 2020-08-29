package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import com.Grid.Core.Base;
import com.Grid.Core.Wrapper;
import com.pages.AmazonHomePage;


public class AmazonSearchTest extends Base {

	AmazonHomePage HP = AmazonHomePage.getInstance();
	Wrapper baseobj = Wrapper.getInstance();
	
	@Test(description = "Verify that user is able to Search product sucessfully.")
	@Tag(name = "Regression")
	public void advanceSearch() {
		test = extent.startTest("Verify that user is able to Search product sucessfully.").assignCategory("Amazon Advance Search");
		HP.searchKeyword("playing kitchen set");
		baseobj.holdon(5);
		Assert.assertTrue(baseobj.currentPageURL().contains("/advancedsearch"));
	}
	
	
}
