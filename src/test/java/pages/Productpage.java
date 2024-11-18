package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Productpage {
	protected WebDriver driver;	
	@FindBy(name="quantity")
	WebElement selectclass;
	@FindBy(xpath="//*[@id=\"three\"]/div/form/div/div[8]/ul/li/input")
	WebElement buybutton;
	

	public  Productpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void productbuy() {
	Select s = new Select(selectclass);
	s.selectByValue("2");
	buybutton.click();
	}
	
	
}
