package com.reports.crossBrowser;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class LoginPassFail {
	private WebDriver driver;
	By singInLocator = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
	By authLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By emailLocator = By.id("email");
	By passwordLocator = By.id("passwd");
	By btnSingInLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By alertLocator = By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li");

	@BeforeClass
	@Parameters("BrowserType")
	public void beforeClass(String browserType) { 
		if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	  
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	  
		System.out.println("Opening: " + browserType);
	}

	@Test
	public void loginPassFail() {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(emailLocator).sendKeys("asd@asd.com.ar");
			driver.findElement(passwordLocator).sendKeys("password-invalido");
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertEquals(driver.findElement(alertLocator).getText(), "Authentication failed.");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

