package org.example.testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class GridBaseClass {

        public WebDriver driver;
        public Logger logger;
        public Properties p;

    @BeforeClass(groups = {"sanity","regression","datadriven","master"})
    @Parameters({"browser","os"})
    public void setUp( String br,String os) throws FileNotFoundException, IOException {

        //loading config.properties file
        FileReader fileReader= new FileReader("./src//test//Resources//config.properties");
        p=new Properties();
        p.load(fileReader);

        logger = LogManager.getLogger(this.getClass());

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("linux")){
                capabilities.setPlatform(Platform.LINUX);
            }
            else {
                throw new IllegalArgumentException("No matching OS: " + os);
            }

            //Browser
            switch(br.toLowerCase()) {
                case "chrome" :capabilities.setBrowserName("chrome");break;
                case "firefox" :capabilities.setBrowserName("firefox");break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + br);
            }

            //launching the browser
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }

       //local execution
       else{
           switch(br.toLowerCase()) {
               //launching the browser
                case "chrome" :driver = new ChromeDriver();break;
                case "firefox" :driver=new FirefoxDriver();break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + br);
            }
        }
       //shared logic-runs regardless of remote or local
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


