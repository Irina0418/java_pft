package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("test1"));

    }
  }

  @Test
  public void testContactCreation() throws Exception {
    Thread.sleep(2000);
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
            .withMobile("89628282828").withEmail("ivan@gmail.com").withGroup("test3");
    app.goTo().gotoAddNew();
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
   // Assert.assertEquals(after.size(), before.size()+1);

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



