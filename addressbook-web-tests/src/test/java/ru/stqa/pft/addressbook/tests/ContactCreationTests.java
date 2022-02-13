package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().addAddress(new ContactDate("Ivan", "Ivanov", "Kazan", "225-25-25", "89628282828", "ivan@gmail.com"));
    app.getContactHelper().saveAddress();
    app.getNavigationHelper().gotoHomePage();

  }

  }



