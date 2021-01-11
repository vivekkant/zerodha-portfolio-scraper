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
			
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/header/ul/li[1]/a")).click();
			
			loginIntoKite();
			
			boolean pending = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[5]/div[2]")).getText().toLowerCase().contains("portfolio");
			int divSequence = pending? 3 : 2;
						
			List<WebElement> rows = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/div/div[5]/div[" + divSequence + "]/table/tbody"));
			for (WebElement row : rows) {
				
				CoinEntry entry = new CoinEntry();
				
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
				
				LOG.debug("Parsed: " + entry);
			}
			
			driver.navigate().to("https://console.zerodha.com/users/login");
			
			double cash = getCashHolding(driver);
			LOG.debug("Cash holding: " + cash);
			
			list.add(WebDriverUtils.getCashEntry(cash));
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
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
