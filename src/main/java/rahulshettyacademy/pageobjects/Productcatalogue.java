package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettuacademy.AbstractComponents.AbstrtactComponent;




public class Productcatalogue extends AbstrtactComponent{
	WebDriver driver;
	public Productcatalogue(WebDriver driver) {
	//initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);//this method will help to findby get driver access
		
	}

	
	//we can we=rite above line with the help of page factory like we write in below line
	
	 //List <WebElement>products =driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy=By.cssSelector(".mb-3");
	By addtoCart=By.cssSelector(".card-body button:last-of-type");
	By toastmessage=By.cssSelector(".toast-container");

	public List<WebElement> getproductlist() {
		getelementtobeappreared(productBy);
		return products;
	}
	public WebElement getproductByname(String productname) {
		WebElement prod= getproductlist().stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	public void addproducttocart(String productname) throws InterruptedException {
		WebElement prod=getproductByname(productname);
		prod.findElement(addtoCart).click();
		getelementtobeappreared(toastmessage);
		waitforelementtodisappear(spinner);
	}

	
}
