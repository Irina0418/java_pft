package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().addAddress(new ContactDate("Petr", "Petrov", "Moscow", "777-25-25", "89628282828", "petr@gmail.com"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();

    }
}
