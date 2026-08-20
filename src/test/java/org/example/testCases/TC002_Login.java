package org.example.testCases;

import org.example.pageObjects.HomePage;
import org.example.pageObjects.LoginPage;
import org.example.pageObjects.MyAccountPage;
import org.example.testBase.BaseClass;
import org.example.utils.DataProviderUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC002_Login extends BaseClass {

    @Test(dataProvider = "loginData" , dataProviderClass = DataProviderUtils.class, groups={"datadriven","master"})
    public void verify_login(String email, String password, String exp_result){
        logger.info("*****Starting TC002_Login test ********");

        //Home page
        HomePage hp= new HomePage(driver);
        hp.clickMyAccount();


        hp.clickOpt_login();

        //login
        LoginPage lp=new LoginPage(driver);
        lp.enterLogin_email(email);
        lp.enterLogin_password(password);
        lp.clickLogin_button();

        //myAccount page
        MyAccountPage mp = new MyAccountPage(driver);
        boolean isLoggedIn = mp.isMyAccountPageExists();
        if (exp_result.equalsIgnoreCase("Valid")) {
            Assert.assertTrue(isLoggedIn, "Expected login to succeed for " + email + " but it failed");
        } else {
            Assert.assertFalse(isLoggedIn, "Expected login to fail for " + email + " but it succeeded");
        }

        logger.info("****** Finished TC002_Login test******");

    }
}
