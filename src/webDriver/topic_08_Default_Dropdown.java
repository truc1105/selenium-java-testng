package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	
	@Test
	public void TC_01_ID() {
		
		
		driver.get("https://demo.nopcommerce.com/register");
		
		//cssSelector --> a.ico-register
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Taylor");
		driver.findElement(By.id("LastName")).sendKeys("Swift");
	
		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		day.selectByValue("1");
		
		Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		month.selectByValue("5");
		
		Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		year.selectByValue("1980");
		
		driver.findElement(By.id("Email")).sendKeys("automation"+ getRandom() +"@gmail.com");
		driver.findElement(By.id("Company")).sendKeys("Automation FC");
		driver.findElement(By.id("Password")).sendKeys("12345678");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		 //----------------------Verify
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getAttribute("value"), "1");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"), "5");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), "1980");
	}
	
	public static int getRandom() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
