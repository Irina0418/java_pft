package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    GroupDate group= new GroupDate("test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()+1);

    //Comparator<? super GroupDate> byId= (Comparator<GroupDate>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //int max1= after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupDate> byId=(g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}

