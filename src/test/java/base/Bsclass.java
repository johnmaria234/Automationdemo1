package base;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Bsclass {
	
	protected WebDriver driver;	

	ExtentSparkReporter reporter;
	protected ExtentTest test;
	protected ExtentReports extent;

	@BeforeTest
	public void beftest() { 
		reporter = new ExtentSparkReporter("./Report/myreport1.html");	
		reporter.config().setDocumentTitle("Automation Report:PAYMENT GATEWAY");
		reporter.config().setReportName("Funtional Test");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("hostname", "localhost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("TesterName", "Maria");

		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		

	}
	@BeforeMethod
	public void setup() {
		
		driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	}

	@AfterTest 
	public void teardown() {extent.flush();}
	
	@AfterMethod 
	public void browserclose(ITestResult result) throws IOException {  
	if(result.getStatus()==ITestResult.FAILURE)	{
		test.log(Status.FAIL, "TEST CASE Failed is "+result.getName());
		test.log(Status.FAIL, "TEST CASE Failed is "+result.getThrowable());
		String screenshotpath= ScreenshotMethod(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotpath);}
	else if(result.getStatus()==ITestResult.SKIP) {
		test.log(Status.SKIP, "TEST CASE skipped is "+result.getName()); }
	else if(result.getStatus()==ITestResult.SUCCESS) {
		test.log(Status.PASS, "TEST CASE passed is "+result.getName()); }
	//driver.close(); 
	}
	public static String ScreenshotMethod(WebDriver driver, String screenshotname) throws IOException {
		// TODO Auto-generated method stub
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination ="./screenshots/"+screenshotname+".jpeg";
		FileHandler.copy(src, new File(destination));
		return destination;  }  

}
