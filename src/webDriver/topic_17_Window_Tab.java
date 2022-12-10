package webDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_17_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;

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
	
	
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/");
	
		// lây ID của tab hiện tại
		String basicFormID = driver.getWindowHandle();
		System.out.println("Parent page: " + basicFormID);
		
		//click vào link google để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()= 'GOOGLE']")).click();
		sleepInSecond(2);
		
//		//Lấy hết tất cả ID
//		Set<String> allWindowIDs = driver.getWindowHandles();
//		
//		//vòng lặp duyệt qua và kiểm tra
//		for(String id: allWindowIDs) {
//			if(!id.equals(basicFormID)) {
//				driver.switchTo().window(id);
//				sleepInSecond(2);
//			}
//		}
//		
		switchToWindowById(basicFormID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
				
	}
	
	
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/");
	
		driver.findElement(By.xpath("//a[text()= 'GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");

		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");

	}
	
	@Test
	public void TC_03_Techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		//add Sony Xperia
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']"
				+ "/descendant::a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg>ul>li>span")).getText(), 
				"The product Sony Xperia has been added to comparison list.");
		
		//add Samsung Galaxy
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']"
				+ "/descendant::a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg>ul>li>span")).getText(), 
				"The product Samsung Galaxy has been added to comparison list.");
		
		//Switch to new window
		driver.findElement(By.xpath("//span[text()='Compare']/ancestor::button")).click();
		sleepInSecond(3);
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		driver.close();
		sleepInSecond(2);
		
		//Switch lại page trước
		switchToWindowById("Mobile");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Mobile");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void switchToWindowByPageTitle(String ExpectedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String id: allWindows) {
			driver.switchTo().window(id);
			
			String actualPageTitle = driver.getTitle();
			
			if(actualPageTitle.equals(ExpectedPageTitle)) {
				break;
			}
		}
	}
	
	public void  switchToWindowById(String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for(String id: allWindowIDs) {
			if(!id.equals(otherID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
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
