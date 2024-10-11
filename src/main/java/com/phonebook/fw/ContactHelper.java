package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper extends BaseHelper {
    public final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(textToFind)) return true;
        }
        return false;
    }

    public void addNewContactPositiveData(String name) {
        clickAddLink();
        fillInNewContactForm(new Contact().setName(name).setLastName("LastNameTest").setPhone("1234567890").setEmail("admin@gmail.com").setAddress("Germany, Berlin").setDescription("Some Description"));
        clickSaveContactButton();
    }

    public void addNewContactPositiveDataWODescription(String name) {
        clickAddLink();
        fillInNewContactForm(new Contact().setName(name).setLastName("LastNameTest").setPhone("1234567890").setEmail("admin@gmail.com").setAddress("Germany, Berlin"));
        clickSaveContactButton();
    }

    private void fillInNewContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    private void clickSaveContactButton() {
        click(By.xpath("//b[.='Save']"));
    }

    private void clickAddLink() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void deleteOneContact() {
        click(By.className("contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }

    public void deleteAllContacts() {
        try {
            while (hasContacts()) {
                int sizeBefore = actualSizeOfContacts();
                deleteOneContact();
                wait.until((WebDriver d) -> actualSizeOfContacts() < sizeBefore);
            }
        } catch (NoSuchElementException e) {
            System.out.println("All contacts were deleted");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int actualSizeOfContacts() {
        if (hasContacts()) {
            // Count the number of contacts by contact-item_card__2SOIM class
            return driver.findElements(By.className(CONTACT_LOCATOR)).size();
        }
        return 0;
    }

    private boolean hasContacts() {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }
}
