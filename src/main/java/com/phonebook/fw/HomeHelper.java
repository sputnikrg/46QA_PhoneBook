package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeHelper extends BaseHelper {


    public HomeHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isHomeComponentPresent() {
        System.out.println("Look for 'HomeComponent' on the home page");
        return isElementPresent(By.xpath("//html/body/div/div/div/div/h1"));
    }
}
