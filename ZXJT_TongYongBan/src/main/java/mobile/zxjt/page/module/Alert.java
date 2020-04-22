package mobile.zxjt.page.module;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.wait.WaitUtil;

public class Alert extends PageBase {
	private MobileElement oTextMsg;
	private MobileElement oBtnCancel;
	private MobileElement oBtnOK;

	public void doAccept() {
		oBtnOK.click();
	}

	public void doCancel() {
		oBtnCancel.click();
	}

	public String doGetMsg() {
		return oTextMsg.getText();
	}

	public void tryClose() {
		if (WaitUtil.exists(driver, oBtnCancel, WaitUtil.WAIT_SHORT)) {
			oBtnCancel.click();
			return;
		}
		oBtnOK.click();
	}

	public boolean exists(int seconds) {
		return WaitUtil.exists(driver, oTextMsg, seconds);
	}
	
	public void doReset(){
		if(WaitUtil.exists(driver, oBtnOK, 2)){
			oBtnOK.click();
		}
		
		if(WaitUtil.exists(driver, oBtnCancel, 2)){
			oBtnCancel.click();
		}
	}
}
