package webDriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_09_Button {
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
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()=\"Đăng nhập\"]")).click();
		
		if(driver.findElement(By.xpath("//div[@class='fhs-btn-box']//span[text()='Đăng nhập']")).isEnabled()) {
			System.out.println("Button is disabled");
		}
		
		driver.findElement(By.id("login_username")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456789");
		sleepInSecond(6);
		
		//do iframe -> chuyen qua 1 trang html khac dc nhung vao
		driver.switchTo().frame("moe-onsite-campaign-635f90b38dd87b080a5f719f");
		driver.findElement(By.cssSelector("#close-icon>img")).click();
		sleepInSecond(1);
		//quay tro ve trang html trc do
		driver.switchTo().defaultContent();
		sleepInSecond(3);
		
		if(driver.findElement(By.xpath("//div[@class='fhs-btn-box']//span[text()='Đăng nhập']")).isEnabled()) {
			System.out.println("Button is disabled");
		}else {
			System.out.println("Button is enabled");
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='fhs-btn-box']//span[text()='Đăng nhập']")).isEnabled());

		String rgbacolor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		System.out.println(rgbacolor);
		
		//convert rgba to hexa
		String hexacolor = Color.fromString(rgbacolor).asHex().toUpperCase();
		System.out.println(hexacolor);
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
