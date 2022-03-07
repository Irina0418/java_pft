package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactDate> before = app.getContactHelper().getContactList();
    ContactDate contact = new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1");
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().createContact (contact);
    app.getNavigationHelper().gotoHomePage();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(contact);

    Comparator<? super ContactDate> byId= (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


  }



