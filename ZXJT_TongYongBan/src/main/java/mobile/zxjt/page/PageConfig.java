package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;

public class PageConfig extends PageBase {
	private MobileElement oBtnSearch;
	private MobileElement oEditSearch;
	private MobileElement oEditJY;
	private MobileElement oBtnOK;

	public void doChangeAddress() {
		oBtnSearch.click();
		oEditSearch.sendKeys("kds888");
		oEditJY.clear();
		oEditJY.sendKeys("111.13.63.2:21990");
		oBtnOK.click();
	}

}