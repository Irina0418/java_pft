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
    public void testContactModificationNewVersion() {
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
    }

    @Test
    public void testContactModification() {
        //исправление задания 9
        List<ContactDate> before = app.contact().list();
        int lastId = before.get(before.size()-1).getId();
        app.contact().selectContact(lastId);

        ContactDate contact =
                new ContactDate().withId(lastId).
                        withFirstname("Ivan").
                        withLastname("Ivanov").
                        withAddress("Kazan")
                        .withMobile("89628282828")
                        .withEmail("ivan@gmail.com");
        app.contact().fillContactForm(contact, false);
        app.contact().updateContact();

        app.goTo().gotoHomePage();
        List<ContactDate> after = app.contact().list();
        assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(new ContactDate().withId(lastId).withFirstname("Ivan").withLastname("Ivanov").withAddress("")
                .withMobile("").withEmail(""));
        Comparator<? super ContactDate> byId=(с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);

        assertEquals(before, after);
    }
}



