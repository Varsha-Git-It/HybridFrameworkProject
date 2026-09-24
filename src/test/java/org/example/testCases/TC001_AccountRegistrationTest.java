package org.example.testCases;

import org.example.pageObjects.AccountRegistrationPage;
import org.example.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC001_AccountRegistrationTest extends BaseTest {

       @Test(dataProvider = "form_details")
       public void fill_form(String fname,String lname,String option, String checkbox ){
           AccountRegistrationPage accountRegistrationPage=new AccountRegistrationPage(driver);
           accountRegistrationPage.click_login_continue_btn();
           accountRegistrationPage.enter_first_name(fname);
           accountRegistrationPage.enter_last_name(lname);
           String generated_email=randomAlphanumeric()+"@gmail.com";
           accountRegistrationPage.enter_email(generated_email);
           accountRegistrationPage.enter_telephone(randomNumber());
           String pwd_saved=randomAlphanumeric();
           accountRegistrationPage.enter_password(pwd_saved);
           accountRegistrationPage.re_enter_password(pwd_saved);
           if(option.equalsIgnoreCase("yes")){
               accountRegistrationPage.click_subscribe_yes_btn();
           }else{
               accountRegistrationPage.click_subscribe_no_btn();
           }
           if(checkbox.equalsIgnoreCase("enable")){
               accountRegistrationPage.click_privacy_checkbox();
           }
           accountRegistrationPage.click_registration_continue_btn();
           if(checkbox.equalsIgnoreCase("enable")) {
               Assert.assertTrue(accountRegistrationPage.confirm_heading_display_check(), "Confirmation  Heading is not visible even after successful account registration for this username:" + fname);
           }else{
               Assert.assertTrue(accountRegistrationPage.alert_display_check(),"Expected privacy policy alert to appear when checkbox is disabled, but it did not, for username:" + fname);
           }
       }

       @DataProvider(name="form_details")
       public Object[][]  fill_form_data(){
           Object[][] form_data={
                   {"pramila","chowdary","yes","enable"},
                   {"sushma","vrutha","no","enable"},
                   {"vrinda","varma","no","disable"},
                   {"sugosh","chattopadhya","yes","enable"},
                   {"bharat","bhandarkar","no","disable"},
                   {"karishma","sen","yes","enable"},
                   {"bhoomi","saras","no","enable"},
                   {"swara","manjari","no","disable"}
           };
           return form_data;
       }

}
