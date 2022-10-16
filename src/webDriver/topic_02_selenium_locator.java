package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_02_selenium_locator {
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
		
		// Open Register
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	@Test
	public void TC_01_ID() {
		//phải tìm đc element trên html
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}
	
	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		
		//Nhập text vào search box
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}
	
	@Test
	public void TC_03_Name() {
		//click vào advanced search checkbox
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	public void TC_04_TagName() {
		System.out.println(driver.findElement(By.tagName("input")).getSize());
	}
	
	@Test
	public void TC_05_LinkText() {
		// đường dẫn tuyệt đối
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText() {
		// đường dẫn tương đối
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("AutomationFC");
	}
	
	@Test
	public void TC_08_XPath() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Taylor");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
