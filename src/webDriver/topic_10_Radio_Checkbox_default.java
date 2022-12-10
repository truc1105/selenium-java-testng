package webDriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

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

public class topic_10_Radio_Checkbox_default {
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
	/*
	@Test
	public void TC_01_Jotform() {
		//chọn checkbox: Cancer - Fainting Spells
		driver.get("https://automationfc.github.io/multiple-fields/");
		//driver.findElement(By.xpath("//input[@value ='Cancer']")).click();
		//driver.findElement(By.xpath("//input[@value ='Fainting Spells']")).click();
		
		//dung ham
		checkToCheckboxOrRadio("//input[@value ='Cancer']");
		checkToCheckboxOrRadio("//input[@value ='Fainting Spells']");
		
		//Verify chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value ='Cancer']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value ='Fainting Spells']")).isSelected());
		
		//Chọn radio 5+ days - 1-2 cups/day
		driver.findElement(By.xpath("//input[@value = '5+ days']")).click();
		driver.findElement(By.xpath("//input[@value = '1-2 cups/day']")).click();
		
		//Verify chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value = '1-2 cups/day']")).isSelected());
		
		// Bỏ chọn checkbox
		driver.findElement(By.xpath("//input[@value ='Cancer']")).click();
		driver.findElement(By.xpath("//input[@value ='Fainting Spells']")).click();
		
		//Verify đc bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value ='Cancer']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value ='Fainting Spells']")).isSelected());
		
		//Bỏ chọn radio
		driver.findElement(By.xpath("//input[@value = '3-4 days']")).click();
		driver.findElement(By.xpath("//input[@value = '5+ cups/day']")).click();
		
		//Verify bỏ chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value = '5+ days']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value = '1-2 cups/day']")).isSelected());
	}
	
	@Test
	public void TC_02_Jotform_SelectAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");


		List <WebElement> checkboxList = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement tempCheckbox: checkboxList) {
			if(!tempCheckbox.isSelected()) {
				tempCheckbox.click();
				sleepInSecond(1);
			}
		}
		
		//verify checkbox duoc chon
		for (WebElement tempCheck: checkboxList) {
			Assert.assertTrue(tempCheck.isSelected());
		}
	}
	*/
	@Test
	public void TC_03_KendoUI_SelectAll() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);
		
		checkToCheckboxOrRadio("//label[text()= 'Luggage compartment cover']/preceding-sibling::input");
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()= 'Luggage compartment cover']"
				+ "/preceding-sibling::input")).isSelected());
	}


	
	public void checkToCheckboxOrRadio(String xpathLocator) {
		if(!driver.findElement(By.xpath(xpathLocator)).isSelected())
			driver.findElement(By.xpath(xpathLocator)).click();
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
