package mobile.zxjt.navigator;

import mobile.zxjt.page.PageConfig;
import mobile.zxjt.page.PageLogin;
import mobile.zxjt.page.PageSplash;
import mobile.zxjt.page.base.PageManager;
import mobile.zxjt.testng.StopException;
import up.light.supports.navigator.INavListener;
import up.light.supports.navigator.IUiNode;

public class NavListenerImpl implements INavListener {
	private static final String PASSWORD = "123321";
	private static final String PREFIX = "【两融】";
	private static final String ATTR_NAME = "custom:checkLogin";
	private boolean hasStarted;
	private boolean PTJY_Logined;
	private boolean RZRQ_Logined;

	@Override
	public void beforeNavigate(String target) {
		if (!hasStarted) {
			// 等待进入主界面并关闭对话框
			PageSplash splash = PageManager.getPage(PageSplash.class);
			splash.doWaitForStart();
			splash.doCloseAlert();
			// 修改交易地址
			PageConfig config = PageManager.getPage(PageConfig.class);
			config.doChangeAddress();
			hasStarted = true;
		}
	}

	@Override
	public void beforeAction(boolean enter, IUiNode node) {
	}

	@Override
	public void afterAction(boolean enter, IUiNode node) {
		// 登录检查
		if (enter) {
			Boolean check = (Boolean) node.getAttribute(ATTR_NAME);
			boolean rzrq = node.getName().startsWith(PREFIX);

			if (Boolean.TRUE == check && Boolean.FALSE == isLogined(rzrq)) {

				PageLogin page = PageManager.getPage(PageLogin.class);
				try {
					page.doLogin(getUsername(rzrq), PASSWORD);
				} catch (Exception e) {
					throw new StopException(e);
				}

				if (rzrq) {
					RZRQ_Logined = true;
				} else {
					PTJY_Logined = true;
				}
			}
		}
	}

	@Override
	public void afterNavigate(String target) {
	}

	private boolean isLogined(boolean rzrq) {
		if (rzrq)
			return RZRQ_Logined;
		return PTJY_Logined;
	}

	private String getUsername(boolean rzrq) {
		if (rzrq)
			return "99000002";
		return "80316041";
	}

}
