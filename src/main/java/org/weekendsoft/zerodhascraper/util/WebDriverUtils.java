package org.weekendsoft.zerodhascraper.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.weekendsoft.zerodhascraper.model.CoinEntry;

public class WebDriverUtils {
	
	public static String getTextNode(WebElement e) {
	    String text = e.getText().trim();
	    List<WebElement> children = e.findElements(By.xpath("./*"));
	    for (WebElement child : children)
	    {
	        text = text.replaceFirst(child.getText(), "").trim();
	    }
	    return text;
	}
	
	public static double getValueFromText(String text) {
		
		String numerical = "0123456789.";
		
		StringBuilder sb = new StringBuilder();
		for(char c : text.toCharArray()) {
			if (numerical.indexOf(c) > -1) sb.append(c);
		}
		
		if (sb.length() > 0) return Double.parseDouble(sb.toString());
		else return -1.0;
		
	}
	
	public static CoinEntry getCashEntry(double cash) {
		CoinEntry entry = new CoinEntry();
		
		entry.setName("Cash Holding");
		entry.setUnit(cash);
		entry.setNav(1);
		entry.setInvestment(cash);
		entry.setCurrent(cash);
		entry.setGain(0);
		entry.setGainper(0);
		
		return entry;
	}

	public static CoinEntry getSmallCaseEntry(double invested, double current) {
		CoinEntry entry = new CoinEntry();
		
		entry.setName("Small Case Investment");
		entry.setUnit(invested / 100);
		entry.setNav((current * 100) / invested);
		entry.setInvestment(invested);
		entry.setCurrent(current);
		entry.setGain(current - invested);
		entry.setGainper(((current - invested) * 100) / invested);
		
		return entry;
	}
	
	public static CoinEntry getSmallCaseEntry(String invested, String current) {
		return getSmallCaseEntry(getValueFromText(invested), getValueFromText(current));
	}
}
