package org.weekendsoft.zerodhascraper.rpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SmallCaseInvestmentUtilTest {

	@Test
	void test() throws InterruptedException {
		
		String username = System.getProperty("zerodha_username");
		String password = System.getProperty("zerodha_password");
		String pin = System.getProperty("zerodha_pin");
		
		SmallCasePortfolioSummary util = new SmallCasePortfolioSummary(username, password, pin);
		String response = util.downloadSmallCaseInvestment();
		
		assertEquals(response, "");
	}

}
