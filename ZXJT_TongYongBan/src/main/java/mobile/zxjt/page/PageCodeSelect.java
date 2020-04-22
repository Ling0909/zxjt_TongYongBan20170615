package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;

public class PageCodeSelect extends PageBase {
	private MobileElement oEditSearch;
	private MobileElement oItemCode;
	private MobileElement oBtnCancel;

	public void doSelect(String code) {
		oEditSearch.setValue(code);
		oItemCode.click();
	}

	@Override
	public void reset() {
		oBtnCancel.click();
	}

}
