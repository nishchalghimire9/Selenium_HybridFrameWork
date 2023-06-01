package com.tutorials.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testBase.TestBase;
import com.tutorials.qa.testData.ReadFromExcelData;

public class LoginTest extends TestBase {

	public LoginTest() throws IOException {
		super();

	}

	SoftAssert softAssert = new SoftAssert();
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = openApplication(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test(priority = 1, dataProvider = "ninja" , dataProviderClass = ReadFromExcelData.class)
	public void VerifyLoginWithValidCredentials(String Username, String password) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.findElement(By.id("input-email")).sendKeys((prop.getProperty("validEmail")));
		driver.findElement(By.id("input-email")).sendKeys(Username);
		// driver.findElement(By.id("input-email")).sendKeys("ghimirenishchal80@gmail.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.findElement(By.id("input-password")).sendKeys("test");
		//driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));  // this is reading from properties file.
		driver.findElement(By.id("input-password")).sendKeys(password); // this is reading from excel sheet
		driver.findElement(By.cssSelector("input.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		softAssert.assertTrue(driver.findElement(By.xpath("//div[@id = 'content']/child::h2[1]")).isDisplayed());
		softAssert.assertAll();

	}

	@Test(priority = 2)
	public void VerifyLoginWithINValidEmailAndinvalidPassword() {

		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidUsername"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("input.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String actualWarningMessage = driver
				.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordMismatchWarningMessage");
		// softAssert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage)); // it gives boolean result
		softAssert.assertAll();

	}

	@Test(priority = 3)
	public void VerifyLoginWithINValidUsernameAndValidPassword() {

		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidUsername"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("input.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String actualWarningMessage = driver
				.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordMismatchWarningMessage");
		// softAssert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage)); // it gives boolean result
		softAssert.assertAll();

	}

	@Test(priority = 4)
	public void VerifyNoUserNameandPassword1() {

		driver.findElement(By.cssSelector("input.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String actualWarningMessage = driver
				.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordMismatchWarningMessage");
		// softAssert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage)); // it gives boolean result
		softAssert.assertAll();

	}
//
//	@AfterMethod
//	public void teardown() {
//		driver.quit();
//	}

}
