package practicePom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class PracticeBaseClass {
	WebDriver driver = null;
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	
	@Parameters("browser")
	@BeforeClass
	public void LaunchBrowser(@Optional ("chrome") String BROWSER) {
		System.out.println("BEforeCalss executed");
		
		if(BROWSER.equals("chrome")) {
		driver= new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		
	}
	
	@Parameters({"url","username","password"})
	@BeforeMethod
	public void login(@Optional("http://localhost:8888/") String url ,@Optional("admin") String username,@Optional("admin") String password) throws Throwable
	{
		System.out.println("BeforeMethod executed");
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(url, username, password);

	}
	

	@AfterMethod
	public void logout() throws Throwable
	{
		HomePage hp = new HomePage(driver);
		hp.logOutOperation();
		
		System.out.println("After Method");
	}

	@AfterClass
	public void ac() {
		System.out.println("After Class");
	}
	
}
