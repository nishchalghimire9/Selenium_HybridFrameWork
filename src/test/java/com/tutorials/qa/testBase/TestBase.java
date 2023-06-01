package com.tutorials.qa.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorials.qa.utilties.Utils;

public class TestBase {

	
	public WebDriver driver;
	public ChromeOptions options;  // this is basically used to passed the argument. for instance to open in incognito mode.

	public Properties prop;
	public FileInputStream ip;
	
	public Properties dataProp;
	
	// create constructor of TestBase
	public TestBase() throws IOException {
		 prop = new Properties();
		 ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorials\\qa\\config\\config.properties");
		 prop.load(ip);
		 
		 dataProp = new Properties();
		 ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorials\\qa\\testData\\dataProp.properties");
		 dataProp.load(ip);
	}
	
	public WebDriver openApplication(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			
			options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			options.addArguments("--headless"); // // it will not allowed to display the browser and run in the background.
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
			options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation", "disable-infobars")); // it just open the browser in headless mode mean it will not display the browser is not control by the automation tool
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
		}
	}

