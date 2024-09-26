package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.Landingpage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.Productcatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class submitordertest extends BaseTest {
	String productname="ZARA COAT 3";
	@Test(dataProvider="getdata",groups= {"purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		Productcatalogue productcatalogue=landingpage.loginApplication(input.get("email"),input.get("passowrd"));
	
		List<WebElement>products= productcatalogue.getproductlist();
		productcatalogue.addproducttocart(input.get("product"));
		CartPage cartpage=productcatalogue.gotocartpage();
		
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectcountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitorder();
		String confirmmsg=confirmationpage.getconfirmationmessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));//this will compare msg we got wih actual msg with ignoring capital letter
		
		
	}
	@Test(dependsOnMethods= {"submitOrder"})//here we use dependsonmethods so it will execute after submitorder methods runs first as then only we can validate
public void orderhistorytest() {
		Productcatalogue productcatalogue=landingpage.loginApplication("nagarwalrohan3@gmail.com","Nagarwal@123");
		OrderPage orderpage=productcatalogue.gotoorderpage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));
}
	
	public String getscreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getdata() throws IOException {
//		HashMap<String, String> map= new HashMap<String,String>();
//		map.put("email", "nagarwalrohan3@gmail.com");
//		map.put("password", "Nagarwal@123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1= new HashMap<String,String>();
//		map1.put("email", "jscnjaksb@gmail.com");
//		map1.put("password", "Nagarwal@123");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>>data=getjsondatatomap(System.getProperty("user.dir")+"src//test//java//rahulshettyacademy//data//PurchasOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
