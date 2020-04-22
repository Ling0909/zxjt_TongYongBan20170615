package mobile.zxjt.page;

import io.appium.java_client.MobileElement;
import mobile.zxjt.page.base.PageBase;
import mobile.zxjt.page.base.PageManager;
import mobile.zxjt.page.module.Loading;
import up.light.supports.pagefactory.DynamicLocator;

public class PageJY extends PageBase {
	private PageCodeSelect mPageCodeSelect = PageManager.getPage(PageCodeSelect.class);
	private Loading mLoading = PageManager.getPage(Loading.class);

	private MobileElement oTextGDDM;
	private MobileElement oEditCode;
	private MobileElement oTextWTFS;
	private DynamicLocator<MobileElement> oMenuWTFS;
	private MobileElement oEditPrice;
	private MobileElement oEditNum;
	private MobileElement oBtnOK;
	private MobileElement oBtnRefresh;

	private boolean inCodeSelect;

	public String doGetGDDM(boolean containMarket) {
		String gddm = oTextGDDM.getText();
		if (containMarket) {
			return gddm;
		}
		return gddm.split(" ")[1];
	}

	public String doInputCode(String code) {
		oEditCode.click();
		inCodeSelect = true;
		mPageCodeSelect.doSelect(code);
		inCodeSelect = false;
		mLoading.waitForLoad();
		return getValue(oEditCode).split("	")[1];
	}

	public void doChooseWTFS(String type) {
		if (type == null || oTextWTFS.getText().equals(type))
			return;
		oTextWTFS.click();
		oMenuWTFS.findElement(type).click();
		mLoading.waitForLoad();
	}

	public String doGetPrice() {
		return getValue(oEditPrice);
	}

	public void doEditPrice(String price) {
		oEditPrice.clear();
		oEditPrice.setValue(price);
	}

	public void doInputNumber(String num) {
		oEditNum.clear();
		oEditNum.setValue(num);
	}

	public void doTrade() {
		oBtnOK.click();
	}

	public void doRefresh() {
		oBtnRefresh.click();
	}

	@Override
	public void reset() {
		if (inCodeSelect) {
			mPageCodeSelect.reset();
		}
		super.reset();
	}

}
