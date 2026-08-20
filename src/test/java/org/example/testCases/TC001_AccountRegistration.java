package org.example.testCases;

import org.example.pageObjects.RegistrationAccountPage;
import org.example.pageObjects.HomePage;
import org.example.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_AccountRegistration extends BaseClass {

    @Test(groups = {"regression","master"})
    public void account_Registration(){

        logger.info("********* Starting TC001_AccountRegistrationTest *********");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link");
            hp.clickRegister();
            logger.info("Clicked on Register link");

            RegistrationAccountPage regpage = new RegistrationAccountPage(driver);
            logger.info("Providing user details");
            regpage.enterFirstName(randomString());
            regpage.enterLastName(randomString().toUpperCase());
            regpage.enterEmail(randomString() + "@gmail.com");
            regpage.enterTelephone(randomNumber());
            String password = randomAlphanumeric();
            regpage.enterPassword(password);
            regpage.enterConfirm_pwd(password);
            regpage.enterSubscribe();
            regpage.EnterAgree();
            regpage.EnterContinue();
            logger.info("Validating expected message...");
            String confirm_registration = regpage.getConfirmation();
            Assert.assertEquals(confirm_registration, "Your Account Has Been Created!");
        }
        catch (Exception e){
            logger.error("Test Failed");
            logger.debug("Debug logs....");
            Assert.fail();
        }
        logger.info("******* Finished TC001_AccountRegistrationTest *********");

    }
}
