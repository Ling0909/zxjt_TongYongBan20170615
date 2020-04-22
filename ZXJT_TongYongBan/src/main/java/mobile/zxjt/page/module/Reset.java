package mobile.zxjt.page.module;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.wait.WaitUtil;

public class Reset extends PageBase {
	private MobileElement oBtnReset;

	public void doReset() {
		Alert alert = getAlert();
		if (alert.exists(WaitUtil.WAIT_SHORT)) {
			alert.tryClose();
		}
		if (WaitUtil.exists(driver, oBtnReset, WaitUtil.WAIT_SHORT)) {
			oBtnReset.click();
		}
	}

}