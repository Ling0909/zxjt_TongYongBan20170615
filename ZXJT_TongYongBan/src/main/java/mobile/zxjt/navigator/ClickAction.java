package mobile.zxjt.navigator;

import org.openqa.selenium.WebElement;

import up.light.IElementFinder;
import up.light.pojo.Locator;
import up.light.supports.navigator.IAction;

public class ClickAction implements IAction {

	@Override
	public void perform(IElementFinder<?> finder, Locator locator) {
		WebElement ele = (WebElement) finder.findElement(locator);
		ele.click();
	}

}
