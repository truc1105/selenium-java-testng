package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
	
	//Khai bao, khoi tao
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
		
		expliciWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Jqueryui() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//1. Click để dropdown xổ ra
		
		/*
		2. Chờ cho tất cả item đc load ra xong
		Phải có hàm wait, nếu chưa tìm thấy sẽ tìm lại trong khoảng wait -> bắt đc locator đại diện cho tất cả item
		
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		*/
		
		//3-1. nếu item được chọn đang hiển thị
		//3-2. nếu item được chọn k hiển thị -> scroll
		
		//4. Kiểm tra text của item -> đúng cái cần -> click
		/*
		List <WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		
		for(WebElement item : allItems) {
			String textItem = item.getText();
			if(textItem.equals("5")) {
				item.click();
			}
		}
		*/
		selectItemCustomDropdown("span#number-button","ul#number-menu div","7");
	}
	
	@Test
	public void TC_02_ID() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		selectItemCustomDropdown("span#number-button","ul#number-menu div","7");
		
	}
	
	public void selectItemCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List <WebElement> allItems = driver.findElements(By.cssSelector("childLocator"));
		for(WebElement item : allItems) {
			String textActualItem = item.getText();
			if(textActualItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
