package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_05_Exercise_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By email = By.id("email");
	By password = By.id("pass");
	By loginButton = By.id("send2");

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	/*
	@Test
	public void TC_01_Empty_Email_And_Password() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
		driver.findElement(email).sendKeys("123@123.123");
		driver.findElement(password).sendKeys("123456");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), 
				"Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Password_Less_Than_6() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
		driver.findElement(email).sendKeys("automation@gmail.com");
		driver.findElement(password).sendKeys("123");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), 
				"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Incorrect_Email_Password() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
		driver.findElement(email).sendKeys("automation@gmail.com");
		driver.findElement(password).sendKeys("123123123");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), 
				"Invalid login or password.");
	}
	*/
	@Test
	public void TC_05_Create_New_Account() {
		Random rand = new Random();
		String emailAddress;
		emailAddress = "Automation" + rand.nextInt(9999) + "@gmail.com";
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Dam");
		driver.findElement(By.id("middlename")).sendKeys("Minh");
		driver.findElement(By.id("lastname")).sendKeys("Dao");
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("123456789");
		driver.findElement(By.id("confirmation")).sendKeys("123456789");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), 
				"Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id = \"header-account\"]//a[@title='Log Out']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
