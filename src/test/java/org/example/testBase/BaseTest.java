package org.example.testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.AA_BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;//instance field-belongs to each individual object;every instance gets its own separate copy of this

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_enabled", false);//suppress "Save password" prompt
        prefs.put("credentials_enable_service", false);//disable chrome's underlying credential management service
        prefs.put("profile.password_manager_leak_detection", false);//to suppress change ypur password prompt
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordChange");

        String executionEnv = AA_BasePage.p.getProperty("execution_env");

        if (executionEnv.equalsIgnoreCase("local")) {

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

        } else if (executionEnv.equalsIgnoreCase("remote")) {
            if(browser.equalsIgnoreCase("chrome")){
                   driver=new RemoteWebDriver(
                           new URL("http://localhost:4444"),options
                   );
            } else if (browser.equalsIgnoreCase("firefox")) {
                   driver=new RemoteWebDriver(
                           new URL("http://localhost:4444"),new FirefoxOptions()
                   );
            } else if (browser.equalsIgnoreCase("edge")) {
                   driver=new RemoteWebDriver(
                           new URL("http://localhost:4444"),new EdgeOptions()
                   );
            }else{
                   throw new IllegalArgumentException("Unsupported browser:"+browser);
            }
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void resetSession(){
        driver.manage().deleteAllCookies();
        driver.get(AA_BasePage.p.getProperty("appUrl"));
    }
    @AfterClass
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
    public String randomString(){
        String generated_randomString= RandomStringUtils.insecure().nextAlphabetic(5);
        return generated_randomString;
    }
    public String randomNumber(){
        String generated_randomNumber=RandomStringUtils.insecure().nextNumeric(10);
        return generated_randomNumber;
    }
    public String randomAlphanumeric(){
        String generated_randomAlphaNumber=RandomStringUtils.insecure().nextAlphanumeric(6);
        return generated_randomAlphaNumber;
    }
}
