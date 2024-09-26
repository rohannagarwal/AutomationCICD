package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettuacademy.AbstractComponents.AbstrtactComponent;




public class Landingpage extends AbstrtactComponent{
	WebDriver driver;
	public Landingpage(WebDriver driver) {
	//initializing
		super(driver);//we have driver here but we need to send to abstract component class so we r using super keyword...so that we can snd the abstract component class constructor will catch
		
		this.driver=driver;
		PageFactory.initElements(driver, this);//this method will help to findby get driver access
		
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//PageFactory
	//we can we=rite above line with the help of page factory like we write in below line
	@FindBy(id="userEmail")
	WebElement userEmails;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public Productcatalogue loginApplication(String email,String password) {
		userEmails.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		Productcatalogue productcatalogue=new Productcatalogue(driver);
		return productcatalogue;
	}
	public String geterrormessage() {
		waitforwebelementtoappear(errormessage);
		return errormessage.getText();
		}
	
	
	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
}
