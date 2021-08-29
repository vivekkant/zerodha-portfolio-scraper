package org.weekendsoft.zerodhascraper.rpa;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.weekendsoft.zerodhascraper.model.CoinEntry;
import org.weekendsoft.zerodhascraper.util.WebDriverUtils;

public class SmallCasePortfolioDownload extends AbstractZerodhaRPA {
	
	private static final Logger LOG = Logger.getLogger(SmallCasePortfolioDownload.class);
	
	public SmallCasePortfolioDownload(String username, String password, String pin) {
		super(username, password, pin);
	}
	
	public SmallCasePortfolioDownload(String username, String password, String pin, WebDriver driver) {
		super(username, password, pin, driver);
	}

	public CoinEntry downloadSmallCaseInvestment() throws Exception {
		
		CoinEntry entry = null;
		
	    try {
	    	
			driver.navigate().to("https://www.smallcase.com/");
			
			driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/header/nav/div[2]/div[1]/button")).click();
			
			List<WebElement> iconList = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[5]/div[2]/div/div[1]/div[2]/div/div/div/div/div[1]/a"));
			for(WebElement zerodhaIcon : iconList) {
				
				String href = zerodhaIcon.getAttribute("href");
				if (href.startsWith("https://kite.zerodha.com")) {
					zerodhaIcon.click();
					break;
				}
			}
			
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(0));
			driver.close();
			driver.switchTo().window(tabs2.get(1));
			
			loginIntoKite();
			
			WebElement currentValueElement = driver.findElement(By.xpath(("//*[@id=\"smallcase-platform\"]/div[5]/div/div[1]/div/div[2]/div[1]/div[2]")));
			LOG.debug("Waiting value:" + currentValueElement.getText() + ":");
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(currentValueElement,"—")));
			
			LOG.debug("After wait value:" + currentValueElement.getText() + ":");
			
			String current = driver.findElement(By.xpath("//*[@id=\"smallcase-platform\"]/div[5]/div/div[1]/div/div[2]/div[1]/div[2]")).getText();
			String invested = driver.findElement(By.xpath("//*[@id=\"smallcase-platform\"]/div[5]/div/div[1]/div/div[2]/div[2]/div[2]")).getText();

			LOG.debug("Smallcase current value:" + current + ":");
			LOG.debug("Smallcase invested value:" + invested + ":");
			
			entry = WebDriverUtils.getSmallCaseEntry(invested, current);
			
			LOG.debug("Got the folloiwng info from Smallcase: " + entry);
			
		} catch (Exception e) {
			
			LOG.error("Unable to download", e);
		}
		
		return entry;
	}
	
	


}
