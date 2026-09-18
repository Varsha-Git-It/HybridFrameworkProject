package org.example.testCases;

import org.example.pageObjects.Login1Page;
import org.example.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_003LoginTest extends BaseTest {

    //checking with invalid creds
    @Test(dataProvider = "invalid_creds")
    public void invalid_LoginisRejected(String username,String pwd) {
        Login1Page login1Page = new Login1Page(driver);
        login1Page.login(username, pwd);
        Assert.assertFalse(login1Page.isLoginSuccessful(), "Login succeeded unexpectedly with invalid credentials" + username);
    }
        @DataProvider(name="invalid_creds")
                public Object[][] invalid_loginData(){
                 Object[][] invalid_Data={
                         {"test56@gmail.com", "abc"},
                         {"sibg%rt", "nmhy345"},
                         {"pom@gmail.com", "89uio"}
                 };
           return invalid_Data;
        }


        //checking with valid creds
       @Test(dataProvider = "valid_creds")
       public void valid_login_logout(String username, String pwd){
        Login1Page login1Page=new Login1Page(driver);
        login1Page.login(username,pwd);
        Assert.assertTrue(login1Page.isLoginSuccessful(),"My Account header not visible even after entering valid credentials" + username);
        login1Page.logout();
       }

       @DataProvider(name="valid_creds")
       public Object[][] valid_loginData(){

        Object[][] valid_data={
                {"laksh@yahoo.com", "Lakshmi"},
                {"abc123@gmail.com", "test@123"}
        };
        return valid_data;
    }


    }


