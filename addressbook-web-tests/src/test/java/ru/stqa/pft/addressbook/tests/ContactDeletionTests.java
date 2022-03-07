package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;


import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
   }
      List<ContactDate> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size()-1);
      app.getContactHelper().deleteSelectedContact();
      app.getContactHelper().acceptDeleteContact();
      app.getNavigationHelper().gotoHomePage();
      Thread.sleep(5000);
      List<ContactDate> after = app.getContactHelper().getContactList();
      int lastIndex = before.size()-1;
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(lastIndex);
      Assert.assertEquals(before, after);

  }

}


