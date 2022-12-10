package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_15_Random_Popup {
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
	public void TC_01_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(7);
		
		WebElement popup = driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])"));
		if(popup.isDisplayed()) {
			driver.findElement(By.cssSelector("input.lepopup-ta-left ")).sendKeys(getRandomEmailAddress());
			sleepInSecond(2);
			driver.findElement(By.cssSelector("a.lepopup-button")).click();
			sleepInSecond(2);
		}
		
		driver.findElement(By.cssSelector("input#s")).sendKeys("Agile Testing");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getRandomEmailAddress() {
		Random rand = new Random();
		return "Testing" + rand.nextInt(99999) + "@gmail.com";
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
