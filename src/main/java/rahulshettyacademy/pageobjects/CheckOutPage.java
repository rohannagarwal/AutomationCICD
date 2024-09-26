package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettuacademy.AbstractComponents.AbstrtactComponent;

public class CheckOutPage extends AbstrtactComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
			super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectcountry;
	
	By result=By.cssSelector(".ta-results");
	
	public void selectcountry(String countryname) {
		Actions a= new Actions(driver);
		a.sendKeys(country, countryname).build().perform();
		//this will take sometime to be visible after entering india
		getelementtobeappreared(By.cssSelector(".ta-results"));
		selectcountry.click();
	}
	public ConfirmationPage submitorder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
