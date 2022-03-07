package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact (new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
        }
        List<ContactDate> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        ContactDate contact= new ContactDate (before.get(before.size()-1).getId(), "Ivan", "Ivanov", "Moscow", "777-25-27", "89628282828", "petr@gmail.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactDate> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        int index= before.get(before.size()-1).getId();
        before.remove(before.size()-1);
        before.add(new ContactDate (index,"Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
        Comparator<? super ContactDate> byId=(с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
