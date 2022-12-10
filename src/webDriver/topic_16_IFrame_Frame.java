package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_16_IFrame_Frame {
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
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Kyna_IFrame() {
		driver.get("https://kyna.vn");
		
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")));
		
		String FBlikes = driver.findElement(By.xpath("//div[@class='lfloat']/div/following-sibling::div")).getText();
		Assert.assertEquals(FBlikes, "165K likes");
		
		//Switch ve main frame
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Taylor");
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
	
		List <WebElement> courseName = driver.findElements(By.xpath("//div[@class='content']/h4"));
		
		for(WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}
	
	
	public void TC_02_HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("12345678");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
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
