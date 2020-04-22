package mobile.zxjt.test.base;

import org.assertj.core.api.Assertions;

import mobile.zxjt.page.PageJY;
import mobile.zxjt.page.base.PageManager;
import mobile.zxjt.page.module.Alert;

public class TestJYBase {
	private PageJY mPage = PageManager.getPage(PageJY.class);

	public PageJY getPage() {
		return mPage;
	}

	/**
	 * 输入交易信息
	 * 
	 * @param info 交易信息
	 * @param wtfs 委托方式
	 * @return 实际确认信息（对话框1）内容
	 */
	public String inputTradeInfo(Info info, String wtfs, boolean containMarket) {
		// 输入代码并校验股票名称
		String vActualName = mPage.doInputCode(info.getCode());
		Assertions.assertThat(vActualName).as("校验名称").isEqualTo(info.getName());
		// 获取股东代码
		String vGddm = mPage.doGetGDDM(containMarket);
		// 选择交易方式
		if (wtfs != null && wtfs.length() > 0) {
			mPage.doChooseWTFS(wtfs);
		}
		// 输入数量
		mPage.doInputNumber(info.getNumber());
		// 获取价格并替换验证点中的{PRICE}、{GDDM}
		String vPrice = mPage.doGetPrice();
		String vCheckPoint1 = info.getConfirmMsg().replace("{PRICE}", vPrice).replace("{GDDM}", vGddm);
		// 点击交易按钮
		mPage.doTrade();
		return vCheckPoint1;
	}

	/**
	 * 校验对话框1内容
	 * 
	 * @param expected 预期内容
	 */
	public void checkConfirmMsg(String expected) {
		// 获取对话框1内容并校验
		Alert vAlert = mPage.getAlert();
		String vActualCheckPoint1 = vAlert.doGetMsg();
		Assertions.assertThat(vActualCheckPoint1).as("校验确认信息").isEqualTo(expected);
		vAlert.doAccept();
		mPage.getLoading().waitForLoad(false);
	}

	/**
	 * 校验对话框2内容
	 * 
	 * @param expected 预期内容
	 * @return 实际内容
	 */
	public String checkResultMsg(String expected) {
		// 获取对话框2内容并校验
		Alert vAlert = mPage.getAlert();
		String vActualCheckPoint2 = vAlert.doGetMsg();
		Assertions.assertThat(vActualCheckPoint2).as("校验结果").contains(expected);
		vAlert.doAccept();
		return vActualCheckPoint2;
	}

	/**
	 * 根据当前价格计算出一个超出涨停或跌停的价格
	 * 
	 * @param price 现价
	 * @param type 类型：{LOW}、{HIGH}
	 * @return 计算出的价格
	 */
	public String getPrice(String price, String type) {
		float num;
		if ("{LOW}".equals(type)) {
			num = 0.01F;
		} else if ("{HIGH}".equals(type)) {
			num = Float.valueOf(price) * 2;
		} else {
			return price;
		}
		return String.valueOf(num);
	}

}
