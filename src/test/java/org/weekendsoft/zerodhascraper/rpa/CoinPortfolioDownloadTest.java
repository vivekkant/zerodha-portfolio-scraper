package org.weekendsoft.zerodhascraper.rpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.weekendsoft.zerodhascraper.model.PortfolioEntry;

class CoinPortfolioDownloadTest {

	@Test
	void test() {
		
		String username = System.getProperty("zerodha_username");
		String password = System.getProperty("zerodha_password");
		String pin = System.getProperty("zerodha_pin");
		
		CoinPortfolioDownload util = new CoinPortfolioDownload(username, password, pin);
		List<PortfolioEntry> list = util.downloadCoinPortfolio();
		
		assertTrue(list.size() > 0);
	}

}
