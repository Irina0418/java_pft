package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
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
            app.contact().create(new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
      //  int index= before.size()-1;
        ContactDate contact= new ContactDate (modifiedContact.getId(), "Ivan", "Ivanov", "Moscow", "777-25-27", "89628282828", "petr@gmail.com", null);
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        //int indexLast= before.get(before.size()-1).getId();
      /*   before.remove(modifiedContact);
        before.add(contact);
       // before.add(new ContactDate (indexLast,"Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
       Comparator<? super ContactDate> byId=(с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);*/
        assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
