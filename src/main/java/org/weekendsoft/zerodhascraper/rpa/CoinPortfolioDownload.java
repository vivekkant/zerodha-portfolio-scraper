package org.weekendsoft.zerodhascraper.rpa;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.weekendsoft.zerodhascraper.model.CoinEntry;
import org.weekendsoft.zerodhascraper.util.WebDriverUtils;

public class CoinPortfolioDownload extends AbstractZerodhaRPA {
	
	private static final Logger LOG = Logger.getLogger(CoinPortfolioDownload.class);

	public CoinPortfolioDownload(String username, String password, String pin) {
		super(username, password, pin);
	}

	public CoinPortfolioDownload(String username, String password, String pin, WebDriver driver) {
		super(username, password, pin, driver);
	}
	
	
	public List<CoinEntry> downloadCoinPortfolio() {
		
		List<CoinEntry> list = new ArrayList<CoinEntry>();
		
		try {
			driver.navigate().to("https://coin.zerodha.com/");
			
			driver.findElement(By.xpath("//*[@id=\"home\"]/div[1]/div/div/div[2]/ul/li[5]/a")).click();
			
			loginIntoKite();
			
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/div/div[1]/a")).click();
			driver.findElement(By.xpath("//*[@id=\"instrument-dropdown\"]/li[2]/a")).click();
						
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/div/div/div/ul/li"));	
			
			for (WebElement row : rows) {
				
				CoinEntry entry = new CoinEntry();
				
				WebElement topDiv = row.findElement(By.tagName("div"));
				if(topDiv.getAttribute("class").contains("holdings-header")) {
					continue;
				}
				
				WebElement name = topDiv.findElement(By.xpath(".//div/div/div[1]/div/div[2]/div[1]"));
				entry.setName(name.getText());
				
				entry.setNav(-1.0d);
				entry.setUnit(-1.0d);
				
				WebElement investment = topDiv.findElement(By.xpath(".//div/div/div[2]/span"));
				entry.setInvestment(WebDriverUtils.getValueFromText(investment.getText()));
				
				WebElement current = topDiv.findElement(By.xpath(".//div/div/div[3]/span"));
				entry.setCurrent(WebDriverUtils.getValueFromText(current.getText()));
				
				double pnl = entry.getCurrent() - entry.getInvestment();
				entry.setGain(pnl);
				
				double pnlper = (pnl * 100) / entry.getInvestment();
				entry.setGainper(pnlper);
				
				list.add(entry);
				
				LOG.debug("Parsed: " + entry);
			}
			
			driver.navigate().to("https://console.zerodha.com/kite/login");
			
			double cash = getCashHolding(driver);
			LOG.debug("Cash holding: " + cash);
			
			list.add(WebDriverUtils.getCashEntry(cash));
			
		} catch (Exception e) {
			LOG.error("Error while downloading ", e);
		} finally {
		}
		
		return list;
	}
	
	public double getCashHolding(WebDriver driver) throws Exception {
		
		double cash = 0;
		
		loginIntoKite();
		String cashText = driver.findElement(By.xpath("//*[@id=\"eq_donut_container\"]/div[1]/div[3]/h3")).getText();
		cash = WebDriverUtils.getValueFromText(cashText);
		
		return cash;
	}
	
}
