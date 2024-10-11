package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("admin_admin_20242@gmail.com", "Password1@");
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTest() {
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size before deletion: " + sizeBefore);
        app.getContactHelper().deleteOneContact();
        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContactHelper().CONTACT_LOCATOR), sizeBefore));
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size after deletion: " + sizeAfterDelete);
        Assert.assertEquals(sizeBefore - 1, sizeAfterDelete, "Count is not equal");
    }

    @Test
    public void deleteAllContactsTests() {
        app.getContactHelper().deleteAllContacts();
        Assert.assertEquals(app.getContactHelper().actualSizeOfContacts(), 0, "Count is not equal");
    }
}
