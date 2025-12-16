package practicePom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.fileUtility.ExcelUtility;

public class GetProductInfoByDataProviderTest {

	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.className("a-button-text")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		String productNamePath = "//span[text()='"+productName+"']/../../../../div[3]/div/div/div/div/div//span[@class='a-price']";
		String price = driver.findElement(By.xpath(productNamePath)).getText();
		System.out.println(price);
		
	}
	//===================fetcing from Excel
	

	@DataProvider
	public Object[][] getData() throws Throwable {
		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowcount("amazon_product");
		Object[][] objArr = new Object[rowCount][2];
		System.out.println("========productName="+eLib.getDataFRomExcel("amazon_product",1, 0));
		for(int i = 0; i<rowCount;i++) {	
		objArr[i][0]= eLib.getDataFRomExcel("amazon_product",i+1, 0);
		objArr[i][1]= eLib.getDataFRomExcel("amazon_product", i+1, 1);
		}
		return objArr;
	}
//===============hardcode
//	@DataProvider
//	public Object[][] getData() {
//		Object[][] objArr = new Object[3][2];
//		objArr[0][0]= "iPhone";
//		objArr[0][1]= "Apple iPhone 15 (128 GB) - Black";
//		
//		objArr[1][0]= "iPhone";
//		objArr[1][1]= "Apple iPhone 15 (128 GB) - Blue";
//		
//		objArr[2][0]= "iphone";
//		objArr[2][1]= "Apple iPhone 15 (128 GB) - Green";
//		return objArr;
//		
//

}
