package com.tutorials.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.testBase.TestBase;

public class SearchProductTest extends TestBase{

	
	public SearchProductTest() throws IOException {
		super();
	
	}
	public static WebDriver driver;
	public static ChromeOptions options;
	SoftAssert softAssert = new SoftAssert();
	@BeforeMethod
	public void setup() {

		driver =openApplication(prop.getProperty("browser"));
		
	}
	@Test(priority =1)
	public void SearchWithValidProduct() {
		driver.findElement(By.xpath("//div[@class = 'input-group']/child::input[1]")).sendKeys(dataProp.getProperty("vaildProduct"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

	}
	@Test(priority =2)
	public void SearchWithINValidProduct() {
		driver.findElement(By.xpath("//div[@class = 'input-group']/child::input[1]")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softAssert.assertTrue(driver.findElement(By.xpath("//p[text() ='There is no product that matches the search criteria.']")).isDisplayed());
		softAssert.assertAll();
	}
	@Test(priority =3)
	public void SearchWithNoProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softAssert.assertTrue(driver.findElement(By.xpath("//p[text() ='There is no product that matches the search criteria.']")).isDisplayed());
		softAssert.assertAll();
		
	}
}
