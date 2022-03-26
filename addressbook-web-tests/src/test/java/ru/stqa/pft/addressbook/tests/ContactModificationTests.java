package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().gotoHomePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                    .withMobile("89628282828").withEmail("ivan@gmail.com"));
            //.withGroup("test3"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactDate modifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate()
                .withId(modifiedContact.getId())
                .withFirstname("Ivan")
                .withLastname("Ivanov")
                .withAddress("Kazan")
                .withMobile("89628282828")
                .withEmail("ivan@gmail.com")
                //.withGroup("test3")
                .withPhone2("")
                .withEmail2("")
                .withEmail3("")
                .withHome("123456789 0")
                .withWorkPhone("")
                .inGroup(groups.iterator().next());

        app.contact().modify(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();
        Contacts b = before.without(modifiedContact).withAdded(contact);

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        //исправление задания 9
      /* List<ContactDate> before9 = app.contact().list();
        int lastId = before9.get(before9.size()-1).getId();
        app.contact().editContact(lastId);

        ContactDate contact9 =
                //new ContactDate().withId (before.get(0).getId(), "Ivan", "Ivanov", "Moscow", "777-25-27", "89628282828", "petr@gmail.com", null);
                new ContactDate().withId(lastId).
                        withFirstname("Ivan").
                        withLastname("Ivanov").
                        withAddress("Kazan")
                        .withMobile("89628282828")
                        .withEmail("ivan@gmail.com");
        app.contact().fillContactForm(contact9, false);
        app.contact().updateContact();

        app.goTo().gotoHomePage();
        List<ContactDate> after9 = app.contact().list();
        assertEquals(after9.size(), before9.size());

       // int index = before.get(before.size()-1).getId();
        before9.remove(before9.size() - 1);
        before9.add(new ContactDate().withId(lastId).withFirstname("Ivan").withLastname("Ivanov").withAddress("")
                .withMobile("").withEmail(""));
        Comparator<? super ContactDate> byId=(с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before9.sort(byId);
        after9.sort(byId);
        assertEquals(before9, after9);*/
    }
}



