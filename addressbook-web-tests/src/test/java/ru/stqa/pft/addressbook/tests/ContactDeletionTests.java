package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().gotoHomePage();
        if(app.contact().all().size() == 0){
            app.contact().create(new ContactDate("Ivan", "Ivanov", "Kazan", null, "89628282828", "ivan@gmail.com", "test1"));
        }
    }

  @Test
  public void testContactDeletion() throws Exception {
     Contacts before = app.contact().all();
     ContactDate deletedContact = before.iterator().next();
      //int index= before.size()-1;
      app.contact().delete(deletedContact);
      Thread.sleep(5000);
      Contacts after = app.contact().all();
     // int lastIndex = before.size()-1;
      assertEquals(after.size(), before.size()-1);

      /*before.remove(deletedContact);
      Assert.assertEquals(before, after);*/
      assertThat(after, equalTo(before.without(deletedContact)));
  }



}


