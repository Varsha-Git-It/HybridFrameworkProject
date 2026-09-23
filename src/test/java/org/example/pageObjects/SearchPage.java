package org.example.pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends AA_BasePage {

    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //WebElement locators
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement search_bar;
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement search_btn;
    @FindBy(css = ".product-layout")
    List<WebElement> resultItems;
    @FindBy(xpath = "//p[contains(.,'no product')]")
    WebElement noResultsMsg;

    //action methods
    public void enter_product_name(String pd_name) {
        search_bar.sendKeys(pd_name);
    }

    public void click_search_btn() {
        search_btn.click();
    }

    public boolean hasResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
            return !resultItems.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean showsNoResultsMsg() {
        try {
            wait.until(ExpectedConditions.visibilityOf(noResultsMsg));
            return true;
        } catch (TimeoutException e) {
            return false;

        }


    }
}







