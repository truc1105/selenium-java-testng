package webDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_13_Actions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new ChromeDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}
	
	
	public void TC_02_Hover_Myntra() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Kids Home Bath");
	}
	
	
	public void TC_03_Hover_Fahasa() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(5);
		driver.findElement(By.cssSelector("button#close-icon")).click();
		
		action.moveToElement(driver.findElement(By.cssSelector("span.ico_menu_white"))).perform();
		driver.findElement(By.xpath("//a[text()='SÁCH HỌC NGOẠI NGỮ']")).click();
	}
	
	
	public void TC_04_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tong so luong number= " + listNumbers.size());
	
		//click vào số bắt đầu, giữ chuột, di chuột đến số kết thúc, thả chuột
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(9)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		System.out.println("Tổng số lượng number đã chọn= " + listNumberSelected.size());
		
		Assert.assertEquals(listNumberSelected.size(), 6);
		
		//verify text đã chọn của element
		
		//define 1 mảng chứa các text cần verify
		String[] listNumberSelectedActual = {"1", "2", "5", "6", "9", "10"};
		 
		//khai báo 1 ARRAYLIST để lưu lại các giá trị đc get trong list bên trên
		ArrayList<String> listNumberSelectedExpected = new ArrayList<>();
		
		//Vòng lặp để duyệt qua list đã chọn ở trên
		for (WebElement number : listNumberSelected) {
			listNumberSelectedExpected.add(number.getText());
		}
		
		//ép kiểu Array qua List
		Assert.assertEquals(listNumberSelectedExpected, Arrays.asList(listNumberSelectedActual));
	}
	

	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tong so luong number= " + listNumbers.size());
	
		//nhấn phím ctrl (chưa nhả ra)
		action.keyDown(Keys.CONTROL).perform();
		
		//Click vào các số cần chọn
		action.click(listNumbers.get(1)).click(listNumbers.get(3)).click(listNumbers.get(4)).click(listNumbers.get(6))
		.click(listNumbers.get(10)).perform();
		
		//nhả phím ctrl
		action.keyDown(Keys.CONTROL).perform();
	}
	
	
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	
	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
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
