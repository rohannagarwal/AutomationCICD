package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.Landingpage;
import rahulshettyacademy.pageobjects.Productcatalogue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"errorhandling"},retryAnalyzer=Retry.class)
	public void loginerrorvalidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productname="ZARA COAT 3";
		
		Productcatalogue productcatalogue=landingpage.loginApplication("nagarwalrohan4@gmail.com","Nagarwal@1");
		landingpage.geterrormessage();
		Assert.assertEquals("Login Successfully", landingpage.geterrormessage());
	}
	
	@Test
	public void producterrorvalidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productname="ZARA COAT 3";
		
		Productcatalogue productcatalogue=landingpage.loginApplication("nagarwalrohan3@gmail.com","Nagarwal@123");
	
		List<WebElement>products= productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
		CartPage cartpage=productcatalogue.gotocartpage();
		
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 333");
		Assert.assertFalse(match);
		
}
}


