package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Bsclass;
import pages.Paymentgatewaypage;
import pages.Productpage;
import utilities.Excelutils;


public class Producttest extends Bsclass {
	@Test
	public void test1productpurchase() {
		test = extent.createTest("Test1 Product page text verification");
		 String s =driver.getPageSource();
		   boolean b;
		   if(s.contains("Mother Elephant With Babies Soft Toy")) {
			    b= true;}
		   else {  b= false;}
		   Assert.assertEquals(b, true); 
	}
	@Test
	public void test2productpurchase() {	
		test = extent.createTest("Test2 product buy, payment gateway fields test");
		SoftAssert softAssert = new SoftAssert();
	    Productpage ob1 =new Productpage(driver);

	    String exp= "https://demo.guru99.com/payment-gateway/process_purchasetoy.php";
	              ob1.productbuy();
	              String act=driver.getCurrentUrl();
	              softAssert.assertEquals(act,exp,"purchase success");
				   Paymentgatewaypage ob2= new Paymentgatewaypage(driver);
				   String xl = "C:\\Users\\Maria-Eldho\\Desktop\\paymentgtwy.xlsx";
		   		   String sheet = "Sheet1";
		   		int rowcount = Excelutils.getRowCount(xl, sheet);
		   	
		   		for(int i=1;i<=rowcount;i++) {
		   			String accountnumber = Excelutils.getCellValue(xl, sheet, i, 0);
		   	 	    String cvv = Excelutils.getCellValue(xl, sheet, i, 1);
		   		    String month = Excelutils.getCellValue(xl, sheet, i, 2);
		   		    String year = Excelutils.getCellValue(xl, sheet, i, 3); 
		   		 String datainput = Excelutils.getCellValue(xl, sheet, i, 4); 
				    ob2.pymtgatewy(accountnumber, cvv, month, year);
				    ob2.alertisdisplayed();
				    String expectkywd ="Payment successfull!";
				    
				
       if(((driver.getPageSource()).contains(expectkywd))){System.out.println("payment success for "+ datainput);
       driver.navigate().back();
       softAssert.assertEquals(datainput, "valid","test is passed");
       }
       else {System.out.println("payment faiure for " + datainput);
       driver.navigate().refresh();
       softAssert.assertEquals(datainput, "valid","test is failed");}
       
				  }
softAssert.assertAll();
				   }
				  
	                 }
	
	
	

