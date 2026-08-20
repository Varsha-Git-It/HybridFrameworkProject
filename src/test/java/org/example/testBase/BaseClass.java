package org.example.testBase;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;


    /*@Parameters({"browser"})
    public void setUp( String br) throws FileNotFoundException, IOException {

        //loading config.properties file
        FileReader fileReader= new FileReader("./src//test//Resources//config.properties");
        p=new Properties();
        p.load(fileReader);

        logger = LogManager.getLogger(this.getClass());

        switch(br.toLowerCase()) {
            case "chrome" :driver = new ChromeDriver();break;
            case "firefox" :driver=new FirefoxDriver();break;
            default:
                System.out.println("INVALID browser name...."); return;
        }*/

    @BeforeClass(groups = {"sanity","regression","datadriven","master"})
    public void setUp() throws IOException{

        //loading config.properties file
        FileReader fileReader= new FileReader("./src//test//Resources//config.properties");
        p=new Properties();
        p.load(fileReader);

        //logger instance
        logger = LogManager.getLogger(this.getClass());

        //driver instance
        driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();
    }
    // Runs after EVERY @Test method, guaranteed — even if an assertion failed
    @AfterMethod(alwaysRun = true)
    public void resetSession(){
        MyAccountPage mp = new MyAccountPage(driver);
        if (mp.isMyAccountPageExists()) {
            mp.click_logout();
            logger.info("Logged out - resetting session for next test iteration");
        }
    }

    @AfterClass(groups={"regression","sanity","datadriven","master"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedstring= RandomStringUtils.insecure().nextAlphabetic(5);
        return  generatedstring;
    }

    public String randomNumber(){
        String generatedstring= RandomStringUtils.insecure().nextNumeric(10);
        return  generatedstring;
    }

    public String randomAlphanumeric(){
        String generatedstring= RandomStringUtils.insecure().nextAlphanumeric(10);
        return generatedstring;
    }
}
