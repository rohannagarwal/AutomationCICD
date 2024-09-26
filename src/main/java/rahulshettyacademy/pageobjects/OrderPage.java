package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettuacademy.AbstractComponents.AbstrtactComponent;

public class OrderPage extends AbstrtactComponent{
	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goToCheckout() {
		checkoutele.click();
		return new CheckOutPage(driver);
	}
	
}
