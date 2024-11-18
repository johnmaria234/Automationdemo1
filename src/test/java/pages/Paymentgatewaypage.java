package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Paymentgatewaypage {

	protected WebDriver driver;	
		
	@FindBy(name="card_nmuber")
	WebElement cardno;	
	@FindBy(name="month")
	WebElement month;
	@FindBy(name="year")
	WebElement year;
	@FindBy(name="cvv_code")
	WebElement cvv;
	
	@FindBy(name="submit")
	WebElement pay;
	
	public  Paymentgatewaypage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); }
	
	public void pymtgatewy(String cardnumber,String cvvcode,String mth,String yr) {
		cardno.sendKeys(cardnumber);
		Select m = new Select(month);
		m.selectByValue(mth);
		
		Select y = new Select(year);
		y.selectByValue(yr);
		
		cvv.sendKeys(cvvcode);
		pay.click();
		
	}
	
	public boolean alertisdisplayed() {
		try {   
		Alert a = driver.switchTo().alert();
        String alrt=a.getText();
        a.dismiss();
        System.out.println(alrt);
			return true; }
		catch(NoAlertPresentException Ex){  return false;}
		
	}
	
	
	
}
