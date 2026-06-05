package org.weekendsoft.zerodhascraper.rpa;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrbisonlineRPA {

    private static final Logger LOG = LogManager.getLogger(OrbisonlineRPA.class);
	
	protected String username;
	protected String password;

    public WebDriver driver = null;

    public OrbisonlineRPA(String username, String password) {
		
		LOG.debug("Initializing the Web Driver");
		
		System.setProperty("webdriver.chrome.driver", "/Users/vivekkant/util/bin/chromedriver");
		this.driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		this.username = username;
		this.password = password;

	}

    public void loginIntoOrbis() throws Exception {

        driver.navigate().to("https://www.orbisonline.in/portal/Account/Login.aspx");

    }

    public void close() {
		driver.close();
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

    public static void main(String[] args) throws Exception {

        OrbisonlineRPA instance = new OrbisonlineRPA("00024477", "");

        instance.loginIntoOrbis();

        instance.close();

    }

}
