package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettuacademy.AbstractComponents.AbstrtactComponent;

public class ConfirmationPage extends AbstrtactComponent{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
			super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (css=".hero-primary")
	WebElement confirmationmessage;
	
	public String getconfirmationmessage() {
		return confirmationmessage.getText();
	}
}
