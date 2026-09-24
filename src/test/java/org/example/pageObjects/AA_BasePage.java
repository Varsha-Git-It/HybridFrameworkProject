package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AA_BasePage {
    WebDriver driver;
    public static Properties p;

    static {
        try {
            InputStream input=AA_BasePage.class.getClassLoader().getResourceAsStream("config.properties"); //returns null if file is not found
            p = new Properties();
            p.load(input);//throws NPE if file is not found
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }


    //constructor
    public AA_BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
