package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class LoginPage extends AA_BasePage  {

    //constructor
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //element locators
    @FindBy(xpath="//input[@id='input-email']")
    WebElement Login_email;
    @FindBy(xpath="//input[@id='input-password']")
    WebElement Login_password;
    @FindBy(xpath="//input[@value='Login']")
    WebElement Login_button;


    //action methods
    public void enterLogin_email(String email){
        Login_email.sendKeys(email);
    }
    public void enterLogin_password(String password){

        Login_password.sendKeys(password);
    }
    public void clickLogin_button(){
        Login_button.click();
    }





}
