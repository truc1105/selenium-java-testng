package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_11_Radio_Checkbox_custom {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
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
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		//khai bao bien By truoc
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	}
	
	
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		By checkedRadio = By.xpath("//span[text()=' Winter ']/preceding-sibling::span/input");
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(checkedRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedRadio).isSelected());
	}
	
	
	public void TC_03_VNDirect() {
		driver.get("https://account-v2.vndirect.com.vn/");
		
		//By termCheckbox = By.xpath("//input[@name='acceptTerms']");
		By termCheckbox = By.xpath("//p[contains(text(), 'Tôi đề nghị mở tài khoản')]/parent::span/preceding-sibling::input");
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_ID() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
	
		//verify trc khi click
		//driver.findElement(canThoRadio).getAttribute("aria-checked");
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
	
		driver.findElement(canThoRadio).click();
		sleepInSecond(3);
		
		//verify sau khi click 
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");

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
		driver.quit();
	}
}
