package listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.UtilityClassObject;
import generic_BaseClassUtility.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	public static ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("====Report configuration===");
		// ============spark report config
		String timeStamp = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report"+timeStamp+".html");
		spark.config().setDocumentTitle("CRM TestSuite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// =================add env inofrmarion and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome-100");
	}

	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==========" + result.getMethod().getMethodName() + "=======Start=====");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"==>STARTED<==");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==========" + result.getMethod().getMethodName() + "=======End=====");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==>COMPLETED<==");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot) UtilityClassObject.getDriver();
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		String timeStamp = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath,testName+timeStamp);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==>FAILED<==");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("=====Report Backup=====");
		report.flush();
	}


}
