package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_03_XPath_part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//div[@class = 'form frmRegister']//button[text() = 'ĐĂNG KÝ']")).click();
		//driver.findElement(By.id("txtFirstname-error")).getText();
		
		//verify
		//assertEquals: expected result = Actual result
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");

	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Taylor Swift");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123.g");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@123.g");
		driver.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id = 'txtCPassword']")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0786307279");
		
		driver.findElement(By.xpath("//div[@class = 'form frmRegister']//button[text() = 'ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_Incorrect_Email() {
		
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		
	}
	
	@Test
	public void TC_05_Incorrect_Password() {
		
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Taylor Swift");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123.g");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@123.g");
		driver.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id = 'txtCPassword']")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0786");
		
		driver.findElement(By.xpath("//div[@class = 'form frmRegister']//button[text() = 'ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
