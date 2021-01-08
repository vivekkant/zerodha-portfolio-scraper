package org.weekendsoft.zerodhascraper.rpa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.weekendsoft.zerodhascraper.model.PortfolioEntry;
import org.weekendsoft.zerodhascraper.util.WebDriverUtils;

public class CoinPortfolioDownload extends AbstractZerodhaRPA {

	public CoinPortfolioDownload(String username, String password, String pin) {
		super(username, password, pin);
	}
	
	public List<PortfolioEntry> downloadCoinPortfolio() {
		
		List<PortfolioEntry> list = new ArrayList<PortfolioEntry>();
		
		System.setProperty("webdriver.chrome.driver", "/Users/vivekkant/util/bin/chromedriver");
	    WebDriver driver = new ChromeDriver();
	    
		try {
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			driver.navigate().to("https://coin.zerodha.com/");
			
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/header/ul/li[1]/a")).click();
			
			driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(this.username);
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(this.password);
			driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[4]/button")).click();
			
			driver.findElement(By.xpath("//*[@id=\"pin\"]")).sendKeys(this.pin);
			driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[3]/button")).click();
			
			List<WebElement> rows = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/div/div[5]/div[2]/table/tbody"));
			for (WebElement row : rows) {
				
				PortfolioEntry entry = new PortfolioEntry();
				
				List<WebElement> cols = row.findElements(By.tagName("td"));
				
				String fundName = cols.get(0).findElement(By.tagName("a")).getText();
				entry.setName(fundName);
				
				String units = cols.get(1).getText();
				entry.setUnit(WebDriverUtils.getValueFromText(units));
				
				String nav = WebDriverUtils.getTextNode(cols.get(3).findElement(By.tagName("span")));
				entry.setNav(WebDriverUtils.getValueFromText(nav));
				
				String investment = cols.get(4).findElement(By.tagName("span")).getText();
				entry.setInvestment(WebDriverUtils.getValueFromText(investment));
				
				String current = cols.get(5).findElement(By.tagName("span")).getText();
				entry.setCurrent(WebDriverUtils.getValueFromText(current));
				
				String pnl = WebDriverUtils.getTextNode(cols.get(6).findElement(By.tagName("span")));
				entry.setGain(WebDriverUtils.getValueFromText(pnl));
				
				String pnlper = cols.get(6).findElement(By.tagName("span")).findElement(By.tagName("div")).getText();
				entry.setGainper(WebDriverUtils.getValueFromText(pnlper));
				
				list.add(entry);
				
				System.out.println(entry);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			driver.quit();
		}
		
		return list;
	}
	


}
