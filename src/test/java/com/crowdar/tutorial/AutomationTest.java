package com.crowdar.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTest {
	
	private WebDriver driver;
	By singInLocator = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
	By authLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By emailLocator = By.id("email");
	By passwordLocator = By.id("passwd");
	By btnSingInLocator = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By myAccountLocator = By.xpath("//*[@id=\"columns\"]/div[1]/span[2]");
	By alertLocator = By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li");

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
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

	@Test
	public void loginUserFail() throws InterruptedException {
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

	@Test
	public void loginPassFail() throws InterruptedException {
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

	@Test
	public void loginUserNone() throws InterruptedException {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(passwordLocator).sendKeys("crowdar");
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertEquals(driver.findElement(alertLocator).getText(), "An email address required.");
	}

	@Test
	public void loginPassNone() throws InterruptedException {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(emailLocator).sendKeys("asd@asd.com.ar");
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertEquals(driver.findElement(alertLocator).getText(), "Password is required.");
	}

	@Test
	public void loginUserAndPassNone() throws InterruptedException {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertEquals(driver.findElement(alertLocator).getText(), "An email address required.");
	}

	@Test
	public void loginFailed() throws InterruptedException {
		driver.findElement(singInLocator).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(authLocator));
		if (driver.findElement(authLocator).isDisplayed()) {
			driver.findElement(btnSingInLocator).click();
		}
		else {
			System.out.print("Sing In pages was not found");
		}
		assertEquals(driver.findElement(alertLocator).getText(), "Test failed.");
	}

}