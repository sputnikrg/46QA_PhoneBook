package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() {
        logger.info("********************** TESTING IN PROGRESS *************************");
//        app.init();
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        app.init();
    }


    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {
        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is started: [" + method.getName() + "], with data: [" + parameters + "]");
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "], with data: [" + parameters + "]");
        } else {
            // Check for an alert and close it with isAlertPresent
            if (app.getUserHelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            // Now take a screenshot
            String screenshotPath = app.getUserHelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }


    @AfterSuite(enabled = false)
    public void tearDown() {
        logger.info("********************** ALL TEST END *************************");
        app.stop();
    }
}
