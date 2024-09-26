package rahulshettuacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstrtactComponent {

	WebDriver driver;
	public AbstrtactComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	
	WebElement cartHeader;
@FindBy(css="[routerlink*='myorders']")
	
	WebElement orderHeader;

	public void getelementtobeappreared(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
		
	}
	
	public void waitforwebelementtoappear(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	
	
	public CartPage gotocartpage() {
		cartHeader.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	public OrderPage gotoorderpage() {
		orderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	
	
	public void waitforelementtodisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//
//		 wait.until(ExpectedConditions.invisibilityOf(ele));

	}
}
