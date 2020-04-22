package mobile.zxjt.page.base;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import mobile.zxjt.driver.DriverFactory;
import mobile.zxjt.page.module.Alert;
import mobile.zxjt.page.module.Loading;
import mobile.zxjt.page.module.Reset;
import up.light.LightContext;
import up.light.Platforms;
import up.light.folder.FolderTypes;
import up.light.supports.pagefactory.ILocatorFactory;
import up.light.supports.pagefactory.JsonLocatorFactory;
import up.light.supports.pagefactory.PageFactory;

public abstract class PageBase {
	protected static AppiumDriver<MobileElement> driver = DriverFactory.getDriver();
	private static final ILocatorFactory FACTORY = new JsonLocatorFactory(
			LightContext.getFolderPath(FolderTypes.REPOSITORY));

	private Reset reset;

	public PageBase() {
		PageFactory.setFactory(FACTORY);
		PageFactory.initElements(DriverFactory.getFinder(), this, MobileElement.class);
	}

	public Alert getAlert() {
		return PageManager.getPage(Alert.class);
	}

	public void reset() {
		// 延迟实例化，防止无限递归
		if (reset == null) {
			reset = PageManager.getPage(Reset.class);
		}
		reset.doReset();
	}

	public Loading getLoading() {
		return PageManager.getPage(Loading.class);
	}

	protected String getValue(WebElement e) {
		if (Platforms.IOS == LightContext.getPlatform()) {
			return e.getAttribute("value");
		}
		return e.getAttribute("text");
	}

}
