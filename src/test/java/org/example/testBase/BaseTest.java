package org.example.testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.AA_BasePage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class BaseTest {
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"browser","os"})
    public void setUp(String browser,@Optional("linux") String os) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_enabled", false);//suppress "Save password" prompt
        prefs.put("credentials_enable_service", false);//disable chrome's underlying credential management service
        prefs.put("profile.password_manager_leak_detection", false);//to suppress change ypur password prompt
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordChange");

        String executionEnv = AA_BasePage.p.getProperty("execution_env");
        logger.info("Running on browser={}, os={}, env={}", browser, os, executionEnv);

        if (executionEnv.equalsIgnoreCase("local")) {

            if (browser.equalsIgnoreCase("chrome")) {
                driver= new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver= new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                 driver= new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

        } else if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities=new DesiredCapabilities();
            //os
            switch(os.toLowerCase()){
                case "windows":capabilities.setPlatform(Platform.WIN10);break;
                case "linux":capabilities.setPlatform(Platform.LINUX);break;
                case "mac":capabilities.setPlatform(Platform.MAC);break;
                default: throw new IllegalArgumentException("unsupported os:"+os);
            }

            //browser
            switch(browser.toLowerCase()){
                case "chrome":capabilities.setBrowserName("chrome");
                capabilities.merge(options);break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                default:throw new IllegalArgumentException("Unsupported browser:"+browser);
            }
            driver=new RemoteWebDriver(new URL(AA_BasePage.p.getProperty("gridUrl")),capabilities);
        }else{
            throw new IllegalArgumentException("Unsupported execution environment:"+executionEnv);
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
