package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationAccountPage extends AA_BasePage {

    //constructor
    public RegistrationAccountPage(WebDriver driver){
        super(driver);
    }

    //Registration page-finding elements
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement Firstname;
    @FindBy(xpath = "//input[@id='input-lastname']") WebElement LastName;
    @FindBy(xpath="//input[@id='input-email']") WebElement Email;
    @FindBy(xpath="//input[@id='input-telephone']") WebElement Phone;
    @FindBy(xpath = "//input[@id='input-password']") WebElement Password;
    @FindBy(xpath = "//input[@id='input-confirm']") WebElement Confirm_pwd;
    @FindBy(xpath = "//label[normalize-space()='Yes']") WebElement Subscribe;
    @FindBy(xpath = "//input[@name='agree']") WebElement Agree;
    @FindBy(xpath = "//input[@value='Continue']") WebElement Continue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement Confirmation_msg;


    //Registion page-Action methods
    public  void enterFirstName(String fname){
        Firstname.sendKeys(fname);
    }
    public  void enterLastName(String lname){
        LastName.sendKeys(lname);
    }
    public  void enterEmail(String email){
        Email.sendKeys(email);
    }
    public  void enterTelephone(String telph){
        Phone.sendKeys(telph);
    }
    public  void enterPassword(String pwd){
        Password.sendKeys(pwd);
    }
    public  void enterConfirm_pwd(String confpwd){
        Confirm_pwd.sendKeys(confpwd);
    }
    public void enterSubscribe(){
        Subscribe.click();
    }
    public void  EnterAgree(){
        Agree.click();
    }
    public  void EnterContinue(){
        Continue.click();
    }
    public String getConfirmation() {
        try {
            return (Confirmation_msg.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
