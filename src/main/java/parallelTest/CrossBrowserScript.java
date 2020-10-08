package parallelTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterMethod;

public class CrossBrowserScript {
	WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		
		if(browser.equalsIgnoreCase("chrome")){
			//create chrome instance
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\eclipse-workspace\\crossbrowsertesting\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("firefox")){
			//set path to chromedriver.exe
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("amirProfile");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\eclipse-workspace\\crossbrowsertesting\\geckodriver.exe");
			//create firefox instance
			driver = new FirefoxDriver(myprofile);
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testParameterWithXML() throws InterruptedException{
		driver.get("http://demo.guru99.com/V4/");
		//Find user name
		WebElement userName = driver.findElement(By.name("uid"));
		//Fill user name
		userName.sendKeys("guru99");
		//Find password
		WebElement password = driver.findElement(By.name("password"));
		//Fill password
		password.sendKeys("guru99");
	}



	@AfterMethod
	public void afterMethod() {
	}

}
