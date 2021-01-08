package org.weekendsoft.zerodhascraper.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

}
