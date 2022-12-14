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
		driver.findElement(By.xpath("//a[text()='S??CH H???C NGO???I NG???']")).click();
	}
	
	
	public void TC_04_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//c???n thao t??c v???i c??? 12 s???
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tong so luong number= " + listNumbers.size());
	
		//click v??o s??? b???t ?????u, gi??? chu???t, di chu???t ?????n s??? k???t th??c, th??? chu???t
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(9)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		System.out.println("T???ng s??? l?????ng number ???? ch???n= " + listNumberSelected.size());
		
		Assert.assertEquals(listNumberSelected.size(), 6);
		
		//verify text ???? ch???n c???a element
		
		//define 1 m???ng ch???a c??c text c???n verify
		String[] listNumberSelectedActual = {"1", "2", "5", "6", "9", "10"};
		 
		//khai b??o 1 ARRAYLIST ????? l??u l???i c??c gi?? tr??? ??c get trong list b??n tr??n
		ArrayList<String> listNumberSelectedExpected = new ArrayList<>();
		
		//V??ng l???p ????? duy???t qua list ???? ch???n ??? tr??n
		for (WebElement number : listNumberSelected) {
			listNumberSelectedExpected.add(number.getText());
		}
		
		//??p ki???u Array qua List
		Assert.assertEquals(listNumberSelectedExpected, Arrays.asList(listNumberSelectedActual));
	}
	

	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//c???n thao t??c v???i c??? 12 s???
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tong so luong number= " + listNumbers.size());
	
		//nh???n ph??m ctrl (ch??a nh??? ra)
		action.keyDown(Keys.CONTROL).perform();
		
		//Click v??o c??c s??? c???n ch???n
		action.click(listNumbers.get(1)).click(listNumbers.get(3)).click(listNumbers.get(4)).click(listNumbers.get(6))
		.click(listNumbers.get(10)).perform();
		
		//nh??? ph??m ctrl
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
