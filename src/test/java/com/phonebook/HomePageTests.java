package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent(),"Item not found on page");
        System.out.println("'HomeComponent' element found on the home page");
    }
}
