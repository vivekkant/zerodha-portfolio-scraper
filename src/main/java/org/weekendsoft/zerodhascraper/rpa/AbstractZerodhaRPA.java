package org.weekendsoft.zerodhascraper.rpa;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractZerodhaRPA {
	
	private static final Logger LOG = Logger.getLogger(AbstractZerodhaRPA.class);
	
	protected String username;
	protected String password;
	protected String pin;
	
	WebDriver driver = null;

	public AbstractZerodhaRPA(String username, String password, String pin, WebDriver driver) {
		
		this.username = username;
		this.password = password;
		this.pin = pin;
		
		this.driver = driver;

	}
	
	public AbstractZerodhaRPA(String username, String password, String pin) {
		
		LOG.debug("Initializing the Web Driver");
		
		System.setProperty("webdriver.chrome.driver", "/Users/vivekkant/util/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		this.username = username;
		this.password = password;
		this.pin = pin;
		
		this.driver = driver;

	}
	
	public void loginIntoKite() throws Exception {
		
		LOG.debug("Trying to Log in");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(this.username);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(this.password);
		WebElement authBtn = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[4]/button"));
		wait.until(ExpectedConditions.elementToBeClickable(authBtn));
		authBtn.click();
		
		driver.findElement(By.xpath("//*[@id=\"pin\"]")).sendKeys(this.pin);
		WebElement pinBtn = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[3]/button"));
		wait.until(ExpectedConditions.elementToBeClickable(pinBtn));
		pinBtn.click();
		
		LOG.debug("Log in completed");
	}
	
	public void close() {
		driver.close();
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
