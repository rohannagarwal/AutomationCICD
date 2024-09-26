package rahulshettyacademy.TestComponent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.Landingpage;

public class BaseTest {
	
	public WebDriver driver;
	public Landingpage landingpage;
	public WebDriver initializedriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	//	String browsername=prop.getProperty("browser");
		
		if(browsername.contains("chrome")) {
		ChromeOptions options=new ChromeOptions();
		
		
		WebDriverManager.chromedriver().setup();
		if(browsername.contains("headless")) {
		options.addArguments("headless");
		}
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));//recomeded to run application in full screen mode
		
	}
		else if(browsername.equalsIgnoreCase("firefox")){
			
			//put gecko driver
			
		}
		else if(browsername.equalsIgnoreCase("edge")){
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public String getscreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
	}
	
	

		public List<HashMap<String, String>> getjsondatatomap(String filePath) throws IOException {
			//read json to string
			String Jsoncontent=FileUtils.readFileToString(new File(filePath),
					StandardCharsets.UTF_8);
			//string to hashmap with jackson databind
			ObjectMapper mapper= new ObjectMapper();
			List<HashMap<String,String>> data=mapper.readValue(Jsoncontent, new TypeReference<List<HashMap<String,String>>>(){	
				
			});
			return data;
			}
	
	@BeforeMethod(alwaysRun=true)
	public Landingpage launchapplication() throws IOException {
		WebDriver driver=initializedriver();
		 landingpage= new Landingpage(driver);
		landingpage.Goto();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
}

