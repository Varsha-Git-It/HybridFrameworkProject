package org.example.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends AA_BasePage{

    //constructor
    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    //element locators
    @FindBy(xpath="//h2[normalize-space()='My Account']")
    WebElement confirmation_heading;

    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logout;

    //action method
    public boolean isMyAccountPageExists(){
        try{
            return  (confirmation_heading.isDisplayed()); // returns true or false wherein element exists in DOM and if it is visible on page-true and if it is hidden-false
        }
        catch (NoSuchElementException e){ // necessary to add- what if the element doesnt exist at all, it throws NosuchElementException
            return false;
        }
    }

    public void click_logout(){
        logout.click();
    }

}
