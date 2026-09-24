package org.example.pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegistrationPage extends AA_BasePage{
    private WebDriverWait wait;

    //constructor
    public AccountRegistrationPage(WebDriver driver){
        super(driver);
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    //element locators
    @FindBy(xpath="//a[normalize-space()='Continue']")
    WebElement login_continue_btn;
    @FindBy(id="input-firstname")
    WebElement first_name;
    @FindBy(id="input-lastname")
    WebElement last_name;
    @FindBy(id="input-email")
    WebElement email;
    @FindBy(id="input-telephone")
    WebElement telephone;
    @FindBy(id="input-password")
    WebElement password;
    @FindBy(id="input-confirm")
    WebElement confirm_pwd;
    @FindBy(xpath="//label[normalize-space()='Yes']")
    WebElement subscribe_yes_btn;
    @FindBy(xpath="//input[@value='0']")
    WebElement subscribe_no_btn;
    @FindBy(xpath="//input[@name='agree']")
    WebElement privacy_checkbox;
    @FindBy(xpath="//input[@value='Continue']")
    WebElement registration_continue_btn;
    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirm_heading;
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    WebElement privacyPolicy_alert;

    //action methods
    public void click_login_continue_btn(){
        login_continue_btn.click();
    }
    public void enter_first_name(String f_name){
        first_name.sendKeys(f_name);
    }
    public void enter_last_name(String l_name){
        last_name.sendKeys(l_name);
    }
    public void enter_email(String usermail){
        email.sendKeys(usermail);
    }
    public void enter_telephone(String phoneNo){
        telephone.sendKeys(phoneNo);
    }
    public void enter_password(String pwd){
        password.sendKeys(pwd);
    }
    public void re_enter_password(String re_pwd){
        confirm_pwd.sendKeys(re_pwd);
    }
    public void click_subscribe_yes_btn(){
        subscribe_yes_btn.click();
    }
    public void click_subscribe_no_btn(){
        subscribe_no_btn.click();
    }
    public void click_privacy_checkbox(){
        privacy_checkbox.click();
    }
    public void click_registration_continue_btn(){
        registration_continue_btn.click();
    }

    public boolean confirm_heading_display_check() {
        try {
            wait.until(ExpectedConditions.visibilityOf(confirm_heading));
            return confirm_heading.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean alert_display_check(){
        try{
            wait.until(ExpectedConditions.visibilityOf(privacyPolicy_alert));
            return privacyPolicy_alert.isDisplayed();
        }catch (TimeoutException e){
            return false;
        }
    }

}
