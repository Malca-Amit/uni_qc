package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentRepoertListeners implements ITestListener, ISuiteListener {
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public ExtentTest getTest() {
		return testReport.get();
	}

	public void setExtentTest(ExtentTest test) {
		testReport.set(test);
	}
	ExtentReports extent;


	public void onFinish(ITestContext arg0) {
	}

	public void onStart(ITestContext arg0) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult arg0) {
	    ScreenshotUtill screenshot = new ScreenshotUtill();
	    String screenShotPath = screenshot.captureScreenshot(arg0.getName());

	    getTest().log(Status.FAIL,
	            MarkupHelper.createLabel(arg0.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));

	    Throwable throwable = arg0.getThrowable();
	    getTest().fail(throwable); // logs the exception message

	    // Highlight your project-related stack trace (tests or pages package)
	    for (StackTraceElement element : throwable.getStackTrace()) {
	        if (element.getClassName().startsWith("tests") || element.getClassName().startsWith("pages")) {
	            String failLocation = element.getClassName() + "." + element.getMethodName()
	                    + "() : Line " + element.getLineNumber();

	            // Highlighted in RED background and white text
	            getTest().log(Status.FAIL,
	                MarkupHelper.createLabel("⛔ Failed at → " + failLocation, ExtentColor.RED));

	            break; // show only first relevant line
	        }
	    }

	    // Full stack trace collapsible for debugging
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    throwable.printStackTrace(pw);
	    getTest().fail("<details><summary>📂 Full Stacktrace (click to expand)</summary><pre>"
	            + sw.toString() + "</pre></details>");

	 // Attach screenshot
	    getTest().fail("Snapshot below: " + getTest().addScreenCaptureFromPath(screenShotPath));

	    extent.flush();
	}



	public void onTestSkipped(ITestResult arg0) {

		getTest().log(Status.SKIP, MarkupHelper.createLabel(arg0.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		getTest().skip(arg0.getThrowable());
		extent.flush();
	}

	public void onTestStart(ITestResult arg0) {
		ExtentTest test = extent.createTest(arg0.getName());
		
		setExtentTest(test);
	}

	public void onTestSuccess(ITestResult arg0) {

		getTest().log(Status.PASS, MarkupHelper.createLabel(arg0.getName() + " Test Case PASSED", ExtentColor.GREEN));
		extent.flush();

	}

	public void onFinish(ISuite suite) {
	    extent.flush();
	}

	public void onStart(ISuite arg0) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		htmlReporter.config().setReportName("Web Automation Result");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "QA");
		
	}

}
