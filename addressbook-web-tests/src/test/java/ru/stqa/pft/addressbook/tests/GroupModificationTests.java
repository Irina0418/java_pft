package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupDate().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.group().all();
        GroupDate modifiedGroup = before.iterator().next();
       // int index= before.size()-1;
        GroupDate group =new GroupDate().withId(modifiedGroup.getId()).withName("test1").withHeader( "test2").withFooter("test3");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        //assertEquals(after.size(), before.size());

       /* before.remove(modifiedGroup);
        before.add(group);
        Comparator<? super GroupDate> byId=(g1,g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
