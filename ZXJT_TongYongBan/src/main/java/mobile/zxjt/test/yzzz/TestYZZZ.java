package mobile.zxjt.test.yzzz;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.zxjt.navigator.Navigator;
import mobile.zxjt.page.PageYZZZ;
import mobile.zxjt.page.base.PageManager;
import mobile.zxjt.page.module.Alert;
import mobile.zxjt.test.base.TestBase;
import up.light.testng.data.CustomRecord;
import up.light.testng.data.DataProviderX;
import up.light.testng.data.annotations.Key;

public class TestYZZZ extends TestBase {
	PageYZZZ mPage = PageManager.getPage(PageYZZZ.class);
	Alert alert = PageManager.getPage(Alert.class);

	@BeforeClass
	public void enterYZZZ() {
		Navigator.navigate("银证转账", mPage);
	}

	@Test(dataProvider = DataProviderX.NAME_LAZY, dataProviderClass = DataProviderX.class)
	public void testYZZZ(@Key("转账方式") String type, @Key("转出账户") String outAccount, @Key("转入账户") String inAccount,
			@Key("转账金额") String num, @Key("银行密码") String yhmm, @Key("资金密码") String zjmm,
			@Key("验证1") String yz1, @Key("验证2") String yz2, CustomRecord record) {
		if (type.equals("银转证")) {
			Navigator.navigate("银行转证券", mPage);
		} else if (type.equals("证转银")) {
			Navigator.navigate("证券转银行", mPage);
		} else {
			throw new RuntimeException("无此转账类型");
		}
		mPage.doChoseOutAccount(outAccount);
		mPage.doChoseInAccount(inAccount);
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
