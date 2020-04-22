package mobile.zxjt.driver;

import org.openqa.selenium.WebDriver;

public interface IDriverCreator {

	<T extends WebDriver> T createDriver();

}
