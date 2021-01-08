package org.weekendsoft.zerodhascraper.rpa;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.weekendsoft.zerodhascraper.util.WebDriverUtils;

public class SmallCasePortfolioSummary extends AbstractZerodhaRPA {

	
	public SmallCasePortfolioSummary(String username, String password, String pin) {
		super(username, password, pin);
	}

	public String downloadSmallCaseInvestment() throws InterruptedException {
		
	    System.setProperty("webdriver.chrome.driver", "/Users/vivekkant/util/bin/chromedriver");  
	    WebDriver driver=new ChromeDriver();  
	    
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    
	    driver.navigate().to("https://www.smallcase.com/"); 
	    
	    driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/header/nav/div[2]/div[1]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"__next\"]/div[5]/div[2]/div/div[1]/div[2]/div/div/div/div/div[1]/a[8]")).click();
	    
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(0));
	    driver.close();
	    driver.switchTo().window(tabs2.get(1));
	    
	    driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(this.username);
	    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(this.password);
	    driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[4]/button")).click();
	    
	    driver.findElement(By.xpath("//*[@id=\"pin\"]")).sendKeys(this.pin);
	    driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[3]/button")).click();
	    
	    WebElement currentValueElement = driver.findElement(By.xpath("//*[@id=\"smallcase-platform\"]/div[4]/div/div[1]/div/div[2]/div[1]/div[2]"));
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(currentValueElement,"-")));
	    
	    String current = driver.findElement(By.xpath("//*[@id=\"smallcase-platform\"]/div[4]/div/div[1]/div/div[2]/div[1]/div[2]")).getText();
	    String returns = driver.findElement(By.xpath("//*[@id=\"smallcase-platform\"]/div[4]/div/div[1]/div/div[2]/div[3]/div[2]")).getText();
	    
	   
	    System.out.println(WebDriverUtils.getValueFromText(current));
	    System.out.println(WebDriverUtils.getValueFromText(returns));
	    
		return "";
	}

}
