package mobile.zxjt.test.rzrq;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.zxjt.navigator.Navigator;
import mobile.zxjt.page.PageRRYZZZ;
import mobile.zxjt.page.base.PageManager;
import mobile.zxjt.page.module.Alert;
import mobile.zxjt.test.base.TestBase;
import up.light.testng.data.CustomRecord;
import up.light.testng.data.DataProviderX;
import up.light.testng.data.annotations.Key;

public class TestRRYZZZ extends TestBase {
	PageRRYZZZ mPage = PageManager.getPage(PageRRYZZZ.class);
	Alert alert = PageManager.getPage(Alert.class);

	@BeforeClass
	public void enterYZZZ() {
		Navigator.navigate("融资融券银证转账", mPage);
	}

	@Test(dataProvider = DataProviderX.NAME_LAZY, dataProviderClass = DataProviderX.class)
	public void testRRYZZZ(@Key("转账方式") String type, @Key("转入或转出银行") String transfer,
			@Key("转账金额") String num, @Key("银行密码") String yhmm, @Key("资金密码") String zjmm,
			@Key("验证1") String yz1, @Key("验证2") String yz2, CustomRecord record) {
		if (type.equals("银行转证券（转入）")) {
			Navigator.navigate("融资融券银行转证券", mPage);
		} else if (type.equals("证券转银行（转出）")) {
			Navigator.navigate("融资融券证券转银行", mPage);
		} else {
			throw new RuntimeException("无此转账类型");
		}
		mPage.doChoseTransfer(transfer);
		if(!yhmm.equals("")&&!yhmm.equals(null))
			mPage.doEditYHPwd(yhmm);
		if(!zjmm.equals("")&&!zjmm.equals(null))
			mPage.doEditZJPwd(zjmm);
		mPage.doEditMoney(num);
		mPage.doFinish();
		String Actual1 = alert.doGetMsg();
		Assertions.assertThat(Actual1).as("校验确认信息").isEqualTo(yz1);
		record.put(TestBase.KEY_CONFIRM, Actual1);
		alert.doAccept();
		String Actual2 = null;
		try{
			Actual2 = alert.doGetMsg();
		}catch(NoSuchElementException ex){
			//ex.printStackTrace();
			System.out.println("======无弹窗默认正确");
			return;
		}
		Assertions.assertThat(Actual2).as("校验是否成功").contains(yz2);
		record.put(TestBase.KEY_CONFIRM, Actual2);
		alert.doAccept();
	}
}
