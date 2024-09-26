package rahulshettyacademy.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.Landingpage;
import rahulshettyacademy.pageobjects.Productcatalogue;

public class stepDefinationimp extends BaseTest{
	public Landingpage landingpage;
	public Productcatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingpage=launchapplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")//we put ^ and $ becoz we are getting data from user
	public void logged_in_username_password(String name,String password) {
		productcatalogue=landingpage.loginApplication(name,password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement>products= productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartpage=productcatalogue.gotocartpage();
		Boolean match=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectcountry("india");
		confirmationpage=checkoutpage.submitorder();

	}
	@Then("{string} message will be displayed on ConfirmationPage")
	public void message_displayed_confirmation(String string) {
		String confirmmsg=confirmationpage.getconfirmationmessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
		driver.close();
	}

	//we can use extension of tidy gerkin then we put feature file then it shows the stepdefination
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) throws Throwable{
		Assert.assertEquals(string, landingpage.geterrormessage());
		driver.close();
	}
	
	
	
	
}
