package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.wait.WaitUtil;

public class PageLogin extends PageBase {
	private MobileElement oEditUsername;
	private MobileElement oEditPassword;
	private MobileElement oEditCheckCode;
	private MobileElement oTextCheckCode;
	private MobileElement oBtnLogin;

	public void doLogin(String username, String password) {
		if (!getValue(oEditUsername).equals(username)) {
			oEditUsername.sendKeys(username);
		}
		oEditPassword.sendKeys(password);
		String code = oTextCheckCode.getText();
		oEditCheckCode.sendKeys(code);
		oBtnLogin.click();
		getLoading().waitForLoad();
		WaitUtil.untilGone(driver, oTextCheckCode, WaitUtil.WAIT_LONG);
	}

}
