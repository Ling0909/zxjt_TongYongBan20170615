package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.page.module.Alert;
import up.light.supports.pagefactory.DynamicLocator;

public class PageYZZZ extends PageBase {
	private MobileElement oBtnChoseOutAccount;
	DynamicLocator<MobileElement> oBtnOutAccount;
	private MobileElement oBtnChoseInAccount;
	DynamicLocator<MobileElement> oBtnInAccount;
	private MobileElement oEditZJPwd;
	private MobileElement oEditYHPwd;
	private MobileElement oEditMoney;
	private MobileElement oBtnChangeSure;
	Alert alert = new Alert();

	
	/**
	 * 选择转出账户
	 * @param Account 转出账户
	 */
	public void doChoseOutAccount(String Account){
		oBtnChoseOutAccount.click();
		oBtnOutAccount.findElement(Account).click();
		alert.doAccept();
	}
	
	/**
	 * 选择转入账户
	 * @param Account 转入账户
	 */
	public void doChoseInAccount(String Account){
		oBtnChoseInAccount.click();
		oBtnInAccount.findElement(Account).click();
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
	
	@Override
	public void reset(){
		alert.doReset();
	}
}
