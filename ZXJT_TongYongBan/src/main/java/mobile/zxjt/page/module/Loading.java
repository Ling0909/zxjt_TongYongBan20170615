package mobile.zxjt.page.module;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.wait.WaitUtil;

public class Loading extends PageBase {
	private MobileElement oImgLoad;

	public void waitForLoad() {
		waitForLoad(true);
	}

	public void waitForLoad(boolean check) {
		while (WaitUtil.exists(driver, oImgLoad, WaitUtil.WAIT_SHORT))
			;
		if (check) {
			Alert alert = getAlert();
			if (alert.exists(WaitUtil.WAIT_SHORT)) {
				throw new RuntimeException(alert.doGetMsg());
			}
		}
	}

}
