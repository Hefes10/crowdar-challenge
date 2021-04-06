package com.reports.automation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class LoginUserFail {
	private WebDriver driver;
	By singInLocator = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
	By authLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By emailLocator = By.id("email");
	By passwordLocator = By.id("passwd");
	By btnSingInLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By alertLocator = By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li");

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void loginUserFail() {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(emailLocator).sendKeys("usuario-invalido");
			driver.findElement(passwordLocator).sendKeys("crowdar");
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		String texto = driver.findElement(alertLocator).getText();
		System.out.print(texto);
		assertEquals(texto, "Invalid email address.");
	}

}