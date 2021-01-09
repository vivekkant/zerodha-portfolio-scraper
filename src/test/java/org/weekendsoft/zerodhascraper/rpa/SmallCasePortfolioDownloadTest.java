package org.weekendsoft.zerodhascraper.rpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.weekendsoft.zerodhascraper.model.CoinEntry;

class SmallCasePortfolioDownloadTest {

	@Test
	void test() throws Exception {
		
		String username = System.getProperty("zerodha_username");
		String password = System.getProperty("zerodha_password");
		String pin = System.getProperty("zerodha_pin");
		
		SmallCasePortfolioDownload util = new SmallCasePortfolioDownload(username, password, pin);
		CoinEntry entry = util.downloadSmallCaseInvestment();
		
		assertNotNull(entry);
	}

}
