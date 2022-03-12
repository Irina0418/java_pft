package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }


    @Test
    public void testGroupDeletion() throws Exception {
     Groups before = app.group().all();
     GroupDate deletedGroup = before.iterator().next();
     app.group().delete(deletedGroup);
     assertThat(app.group().count(), equalTo(before.size()-1));
     Groups after = app.group().all();
     // assertEquals(after.size(), before.size()-1);
     assertThat(after, equalTo(before.without(deletedGroup)));
    /*    before.remove(deletedGroup);
     Assert.assertEquals(before, after);*/

  }

}
