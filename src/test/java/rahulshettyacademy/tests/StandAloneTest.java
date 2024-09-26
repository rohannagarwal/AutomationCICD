package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.Landingpage;

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

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		Landingpage landingpage= new Landingpage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("nagarwalrohan3@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nagarwal@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 List <WebElement>products =driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod= products.stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		 //in above line we created a stream which converted products into stream and from there we took one product and goes to b where the name of product is stored and we compare it with text
		 //ZARA COAT 3 and we gonna store the first ZARA COAT 3 if not found then we return null
		 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();//card body will show the whole card so we traverse by giving space we goes to button
		//but button also were selecting two of them so we search by last-of-type this will show last button and we use prod coz we need of last button of prod which has zara coat 3
		//pop up message we got after adding product to card we can ask the developer about the locator he used or we can quickly try to inspect thatin our case its toast-container
		//we have to wait until that pop up is displayed so we gonna use explicit wait for the item to be displayed
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));//it will wait until locator be visible
		//ng-animating this is the class we see after adding item to cart..we cant inspect it so we have to ask developer
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); this line can decrese performance issue coz it has to wait until invisbility f locator
		//instead we can use
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		//now we have to check if yhe item we selected is visible in cart or not
		//so we using xpath parents to child traverse //*[@class='cartSection']/h3
		//if we want to use css the we use .cartSection h3
		List <WebElement> cartproducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));//we r using anymatch istead of filter here anymatch check if we have any match with name productname
		
		//boollean match show true we we find any match with productname else it shows false
		Assert.assertTrue(match);
		
		
		driver.findElement(By.cssSelector(".totalRow button")).click();//click on checkout button
		//now we have to enter out countrt name in that we are using action class here now
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		//this will take sometime to be visible after entering india
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit ")).click();
		String confirmmsg=driver.findElement(By.cssSelector("hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(" Thankyou for the order."));//this will compare msg we got wih actual msg with ignoring capital letter
		
		
	}

}
