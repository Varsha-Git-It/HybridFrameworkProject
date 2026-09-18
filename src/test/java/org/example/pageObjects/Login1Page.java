package org.example.pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login1Page extends  AA_BasePage {
    WebDriverWait wait;

    public Login1Page(WebDriver driver){
        super(driver);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //element locators
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;
    @FindBy(xpath="//input[@value='Login']")
    WebElement loginBtn;
    @FindBy(xpath="//h2[normalize-space()='My Account']")
    WebElement myAccountHeader;
    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logOutlink;

    //action methods
    public void login(String username,String pwd){
        email.clear();
        email.sendKeys(username);
        password.clear();
        password.sendKeys(pwd);
        loginBtn.click();
    }

    public boolean isLoginSuccessful(){
        try{
            wait.until(ExpectedConditions.visibilityOf(myAccountHeader));
            return true;
        }catch(TimeoutException e){
            return false;
        }
    }

    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(logOutlink)).click();
    }









}
