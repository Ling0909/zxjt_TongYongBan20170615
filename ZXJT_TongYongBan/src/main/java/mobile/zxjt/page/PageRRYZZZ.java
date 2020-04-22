package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.page.module.Alert;
import up.light.supports.pagefactory.DynamicLocator;

public class PageRRYZZZ extends PageBase {
	private MobileElement oBtnChoseTransfer;
	DynamicLocator<MobileElement> TransferAccount;
	private MobileElement oEditZJPwd;
	private MobileElement oEditYHPwd;
	private MobileElement oEditMoney;
	private MobileElement oBtnChangeSure;
	Alert alert = new Alert();

	/**
	 * 选择转账方向中的银行
	 * @param transfer转入转出银行
	 */
	public void doChoseTransfer(String transfer){
		oBtnChoseTransfer.click();
		TransferAccount.findElement(transfer);
		alert.doAccept();
	}
	
	/**
	 * 输入资金密码
	 * @param pwd 资金密码
	 */
	public void doEditZJPwd(String pwd){
		oEditZJPwd.sendKeys(pwd);
	}
	
	public void doEditYHPwd(String pwd){
		oEditYHPwd.sendKeys(pwd);
	}
	
	/**
	 * 输入转账金额
	 * @param money 转账金额
	 */
	public void doEditMoney(String money){
		oEditMoney.sendKeys(money);
	}
	
	/**
	 * 点击确定按钮
	 */
	public void doFinish(){
		oBtnChangeSure.click();
	}
	
}
