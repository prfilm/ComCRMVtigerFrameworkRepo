package generic_BaseClassUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generic.databaseUtility.DataBaseUtility;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.UtilityClassObject;
import generic.webDriverUtility.WebDriverUtility;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

@Listeners(listenerUtility.ListenerImplementation.class)
public class BaseClass {
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(alwaysRun =true)
	public void configBS() throws Throwable {
		dbLib.getDbConnection();
		System.out.println("==Connect to DB, Report config===");
	}

	@Parameters("BROWSER")
	@BeforeClass(alwaysRun =true)
	public void configBC(@Optional ("chrome") String browser) throws Throwable {
		String BROWSER = browser;
				// fLib.getDataFromPropertiesFile("browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		//sdriver = driver;
		UtilityClassObject.setDriver(driver);
		System.out.println("==Launch Browser===");
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
	}

	@BeforeMethod(alwaysRun =true)
	public void configBM() throws Throwable {

		LoginPage lp = new LoginPage(driver);
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
		System.out.println("==Login==");
	}

	@AfterMethod(alwaysRun =true)
	public void configAM() throws Throwable {
		try{
		HomePage hp = new HomePage(driver);
		wLib.waitForPageToLoad(driver);
		hp.logOutOperation();
		System.out.println("==Logout==");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun =true)
	public void configAC() {
		driver.quit();
		System.out.println("==close the Browser==");
	}

	@AfterSuite(alwaysRun =true)
	public void configAS() throws Throwable {
		dbLib.closeDbConnection();
		System.out.println("==Close DB, Report backUp=");
	}

}
