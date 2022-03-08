package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1");
    app.goTo().gotoAddNew();
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

   // contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
   // before.add(contact);

   /* Comparator<? super ContactDate> byId= (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);*/
    //Assert.assertEquals(before, after);

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  }



