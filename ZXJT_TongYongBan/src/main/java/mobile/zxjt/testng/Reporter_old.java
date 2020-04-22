package mobile.zxjt.testng;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import up.light.LightContext;
import up.light.folder.FolderTypes;

public abstract class Reporter_old {
	private static ExtentReports extent;

	public static String initReport() {
		String folder = LightContext.getFolderPath(FolderTypes.REPORT);

		File file = new File(folder);
		if (!file.exists()) {
			file.mkdirs();
		}

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(folder + "/report.html");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("自动化测试报告");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("自动化测试报告");
		htmlReporter.config().setCSS(CssHelper.getCSS());

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Platform", LightContext.getPlatform().name());
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Arch", System.getProperty("os.arch"));
		extent.setSystemInfo("JDK", System.getProperty("java.version"));

		return folder;
	}

	public static ExtentTest createTest(String name) {
		return extent.createTest(name);
	}

	public static void flush() {
		if (extent != null) {
			extent.flush();
		}
	}

}
