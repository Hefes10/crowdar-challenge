package com.reports.crossBrowser;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class LoginExitoso {
	private WebDriver driver;
	By singInLocator = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
	By authLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By emailLocator = By.id("email");
	By passwordLocator = By.id("passwd");
	By btnSingInLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By myAccountLocator = By.xpath("//*[@id=\"columns\"]/div[1]/span[2]");
	  
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
	public void loginExitoso() throws InterruptedException {
		driver.findElement(singInLocator).click();
		Thread.sleep(2000);
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(emailLocator).sendKeys("asd@asd.com.ar");
			driver.findElement(passwordLocator).sendKeys("crowdar");
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertTrue(driver.findElement(myAccountLocator).isDisplayed());
	}
	
	@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
