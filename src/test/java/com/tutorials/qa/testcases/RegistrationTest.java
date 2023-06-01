package com.tutorials.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testBase.TestBase;
import com.tutorials.qa.utilties.Utils;

public class RegistrationTest extends TestBase {
	public RegistrationTest() throws IOException {
		super();
		
	}
	//public static WebDriver driver;

	SoftAssert softAssert = new SoftAssert();
	@BeforeMethod
	public void setup() {
		
		driver =openApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	@Test(priority= 1)
	public void registerAccountWithMandatoryField() {
		
		driver.findElement(By.id("input-firstname")).sendKeys((dataProp.getProperty("firstName")));
		driver.findElement(By.id("input-lastname")).sendKeys((dataProp.getProperty("lastName")));
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys((dataProp.getProperty("telephone")));

		driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));
		driver.findElement(By.id("input-confirm")).sendKeys((prop.getProperty("vaildPassword")));
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualmessage = driver.findElement(By.xpath("//div[@id = 'content']/child::h1[1]")).getText();
		String expectedMessage = "Your Account Has Been Created!";
		softAssert.assertEquals(actualmessage, expectedMessage);
		softAssert.assertAll();
	}
	
	@Test(priority= 2)
	public void registerWithExistingEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys((dataProp.getProperty("firstName")));
		driver.findElement(By.id("input-lastname")).sendKeys((dataProp.getProperty("lastName")));
		driver.findElement(By.id("input-email")).sendKeys((prop.getProperty("validEmail")));
		driver.findElement(By.id("input-telephone")).sendKeys((dataProp.getProperty("telephone")));
		driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));
		driver.findElement(By.id("input-confirm")).sendKeys((prop.getProperty("vaildPassword")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.findElement(By.xpath("//label[@class ='radio-inline']/child::input[1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWaringMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
		String expectedWariningMessage = dataProp.getProperty("emailAddressExistWarningMessage");
		softAssert.assertTrue(actualWaringMessage.contains(expectedWariningMessage));
		softAssert.assertAll();
		
	}
	
	
	@Test(priority= 3)
	public void registerAccountWithAllField() {
		driver.findElement(By.id("input-firstname")).sendKeys((dataProp.getProperty("firstName")));
		driver.findElement(By.id("input-lastname")).sendKeys((dataProp.getProperty("lastName")));
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys((dataProp.getProperty("telephone")));
		driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));
		driver.findElement(By.id("input-confirm")).sendKeys((prop.getProperty("vaildPassword")));;
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}
	@Test(priority= 4)
	public void registerAccountWithoutaggreingPrivacyPolicy() {
		driver.findElement(By.id("input-firstname")).sendKeys((dataProp.getProperty("firstName")));
		driver.findElement(By.id("input-lastname")).sendKeys((dataProp.getProperty("lastName")));
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys((dataProp.getProperty("telephone")));
		driver.findElement(By.id("input-password")).sendKeys((prop.getProperty("vaildPassword")));
		driver.findElement(By.id("input-confirm")).sendKeys((prop.getProperty("vaildPassword")));;
		driver.findElement(By.name("newsletter")).click();
		//driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		softAssert.assertTrue(driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).isDisplayed());
		softAssert.assertAll();
		
		
	}
	
	@Test(priority= 5)
	public void registerAccountWithNofeilds() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'danger alert-dismissible')]")).getText();
		String expectedwarningMessage = dataProp.getProperty("privacyPolicywarningMessage");
		softAssert.assertTrue(actualWarningMessage.contains(expectedwarningMessage));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div[1]")).getText();
		String expectedFirstnamewarningMessage = dataProp.getProperty("firstNameWarningMessage");
		softAssert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstnamewarningMessage));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div[1]")).getText();
		String expectedLastnamewarningMessage = dataProp.getProperty("lastNameWarningMessage");
		softAssert.assertEquals(actualLastNameWarningMessage,expectedLastnamewarningMessage);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actualEmailNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div[1]")).getText();
		String expectedEmailnamewarningMessage = dataProp.getProperty("emailWarningMessage");
		softAssert.assertEquals(actualEmailNameWarningMessage,expectedEmailnamewarningMessage);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div[1]")).getText();
		String expectedTelephonewarningMessage = dataProp.getProperty("TelephoneWarningMessage");
		softAssert.assertEquals(actualTelephoneWarningMessage,expectedTelephonewarningMessage);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div[1]")).getText();
		String expectedPasswordwarningMessage = dataProp.getProperty("passwordWarningMessage");
		softAssert.assertEquals(actualPasswordWarningMessage,expectedPasswordwarningMessage);
		
	softAssert.assertAll();
		
	}		
	@AfterMethod
	public void teardown() {
	driver.quit();
}

}