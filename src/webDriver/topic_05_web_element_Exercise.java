package webDriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_05_web_element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By developmentCheckbox = By.id("development");
	By password = By.id("disable_password");
	By ageRadioDisabled = By.id("radio-disabled");
	By languageJava = By.id("java");

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
	public void TC_01_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
		}
		
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
		}
		
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}	
	}
	
	@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextbox).isEnabled()) {
		System.out.println("Element is enable.");
		}
		
		if (driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("Element is enable.");
			}
		
		if (driver.findElement(developmentCheckbox).isEnabled()) {
			System.out.println("Element is enable.");
			}
		
		if (driver.findElement(password).isEnabled()) {
			System.out.println("Element is enable.");
			}else {
				System.out.println("Element is not enable.");
			}
		
		if (driver.findElement(ageRadioDisabled).isEnabled()) {
			System.out.println("Element is enable.");
			}else {
				System.out.println("Element is not enable.");
			}
	}
	
	@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(languageJava).click();
		
		if(driver.findElement(languageJava).isSelected()) {
			System.out.println("Element is selected.");
		}else {
			System.out.println("Element is not selected");
		}
		sleepInSecond(3);
		
		driver.findElement(languageJava).click();
		
		if(driver.findElement(languageJava).isSelected()) {
			System.out.println("Element is selected.");
		}else {
			System.out.println("Element is not selected");
		}
	}
	
	
	@Test
	public void TC_04_Register_Function() {
		By email_04 = By.id("email");
		By pw_04 = By.id("new_password");
		
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(email_04).sendKeys("susu.0608@gmail.com");
		
		// Verify lowercase
		driver.findElement(pw_04).sendKeys("abc");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
		
		//verify upper case
		//verify number
		//verify special char
		//verify char >= 8
		//verify full data
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
