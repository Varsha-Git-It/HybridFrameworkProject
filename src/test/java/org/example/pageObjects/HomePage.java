package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AA_BasePage {

    //constructor
    public HomePage(WebDriver driver){
        super(driver);
    }

    //Home page
    @FindBy(xpath = "//i[@class='fa fa-user']")
    WebElement MyAccount;
    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
    WebElement Register;
    @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
    WebElement Opt_login;


    //action methods
    public void clickMyAccount(){
        MyAccount.click();
    }
    public void clickRegister(){
        Register.click();
    }
    public void clickOpt_login(){
        Opt_login.click();
    }
}
