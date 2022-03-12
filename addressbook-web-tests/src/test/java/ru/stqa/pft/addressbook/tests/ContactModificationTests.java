package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().gotoHomePage();
        if(app.contact().all().size() == 0){
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                    .withMobile("89628282828").withEmail("ivan@gmail.com").withGroup("test3"));
        }
    }

    @Test
    public void testContactModification(){
       Contacts before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
      //  int index= before.size()-1;
        ContactDate contact = new ContactDate().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                .withMobile("89628282828").withEmail("ivan@gmail.com").withGroup("test3");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        //assertEquals(after.size(), before.size());

        //int indexContact= before.get(before.size()-1).getId();
      // before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        //исправление задания 9
        /*List<ContactDate> before = app.contact().list();
        app.contact().editContact();
        ContactDate contact= new ContactDate (before.get(before.size()-1).getId(), "Ivan", "Ivanov", "Moscow", "777-25-27", "89628282828", "petr@gmail.com", null);
        app.contact().fillContactForm(contact, false);
        app.contact().updateContact();
        app.goTo().gotoHomePage();
        List<ContactDate> after = app.contact().list();
        assertEquals(after.size(), before.size());

        int index = before.get(before.size()-1).getId();
        before.remove(before.size()-1);
        before.add(new ContactDate (index,"Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
        Comparator<? super ContactDate> byId=(с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);*/
    }


}
