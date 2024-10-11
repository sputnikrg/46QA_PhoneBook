package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {

    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("admin_admin_20@gmail.com")
                .setPassword("Password1@"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountPositiveTest2() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password1@";
        app.getUserHelper().register(email, password);
    }

    @Test
    public void createAccountLoginPositiveTest() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password1@";
        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }

    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
      //fillInRegistrationForm(new User("admin_admin_20242@gmail.com", "Password1@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("admin_admin_20@gmail.com")
                .setPassword("Password1@"));
        app.getUserHelper().clickRegistrationButton();
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();

    }
    @AfterMethod(enabled = true)
    public void postConditions() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }
}
