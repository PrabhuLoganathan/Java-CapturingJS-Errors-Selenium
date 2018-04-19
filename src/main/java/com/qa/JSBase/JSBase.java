package com.qa.JSBase;

import java.util.Date;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class JSBase {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/Kranti/Documents/Tools/chromedriver.exe");
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingpreferences);
		//driver = new ChromeDriver(capabilities);
		LoggingPreferences loggingpreferences = new LoggingPreferences();
		loggingpreferences.enable(LogType.BROWSER, Level.ALL);
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.LOGGING_PREFS, loggingpreferences);
		driver = new ChromeDriver(options);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public void JSErrorFinder() {
		LogEntries logentries = driver.manage().logs().get("browser");
		for (LogEntry entry : logentries) {
			System.out.println(new Date(entry.getTimestamp()) + "  " + entry.getLevel() + "  " + entry.getMessage());
		}
	}

	@Test
	public void testMethod() {
		driver.get("http://www.automationpractice.com");
		JSErrorFinder();
	}

}
