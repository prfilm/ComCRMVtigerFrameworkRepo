package objectRepositoryUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products_Page {

	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductIcon;
	
	@FindBy(name="search_text")
	private WebElement searchTextfeild;
	
	@FindBy(name="submit")
	private WebElement searchButton;
}
