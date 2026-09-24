package org.example.testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.pageObjects.AA_BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;//instance field-belongs to each individual object;every instance gets its own separate copy of this

    @BeforeClass
    public void setUp(){
        ChromeOptions options=new ChromeOptions();
        Map<String,Object> prefs=new HashMap<>();
        prefs.put("profile.password_manager_enabled",false);//suppress "Save password" prompt
        prefs.put("credentials_enable_service",false);//disable chrome's underlying credential management service
        prefs.put("profile.password_manager_leak_detection",false);//to suppress change ypur password prompt
        options.setExperimentalOption("prefs",prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordChange");
        driver=new ChromeDriver(options);
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
