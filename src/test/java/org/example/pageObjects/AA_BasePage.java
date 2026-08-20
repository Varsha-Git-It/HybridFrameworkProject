package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AA_BasePage {
    WebDriver driver;
    public static Properties p;

    static {
        try {
            FileReader fileReader = new FileReader("./src/test/Resources/config.properties");
            p = new Properties();
            p.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    public AA_BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
