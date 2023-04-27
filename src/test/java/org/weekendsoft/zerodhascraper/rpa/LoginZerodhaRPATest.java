package org.weekendsoft.zerodhascraper.rpa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LoginZerodhaRPATest {

    @Test
    void test() throws Exception {
        
        String username = System.getProperty("zerodha_username");
        String password = System.getProperty("zerodha_password");
        String pin = System.getProperty("zerodha_pin");
        
        AbstractZerodhaRPA zerodhaRPA = new AbstractZerodhaRPA(username, password, pin);
        WebDriver driver = zerodhaRPA.driver;
        

        driver.navigate().to("https://console.zerodha.com/");
        
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div[2]/button")).click();
          
        zerodhaRPA.loginIntoKite();
     
        
        
    }

}
