package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomeHelper;
import com.phonebook.fw.UserHelper;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;

    UserHelper userHelper;
    HomeHelper homeHelper;
    ContactHelper contactHelper;

    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        //driver = new ChromeDriver();
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("window-size=1920x1080");
            options.addArguments("headless");
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if(browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }
        driver.manage().window().setPosition(new Point(2500, 0)); // only for a teacher
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // implicit
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // explicit

        userHelper = new UserHelper(driver, wait);
        homeHelper = new HomeHelper(driver, wait);
        contactHelper = new ContactHelper(driver, wait);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public HomeHelper getHomeHelper() {
        return homeHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void stop() {
        if (driver != null) {
            driver.quit(); // Stopping the driver after all tests
        }
    }
}
