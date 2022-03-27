package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
         if (app.db().contacts().size() == 0) {
             app.goTo().gotoHomePage();
             app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                    .withMobile("89628282828").withEmail("ivan@gmail.com"));
         }

         if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1"));
            app.goTo().gotoHomePage();
        }
    }

    @Test (enabled = false)
    public void testContactInGroup() {
        ListOfContactsInGroup before = app.db().groupsWithContact();

        Groups groups = app.db().groups();
        ContactDate contactToAddToGroup = app.db().contacts().iterator().next();
        Groups modifiedContactGroups = contactToAddToGroup.getGroups();
        Groups groupsForTest = groups;

        if (groups.size() == modifiedContactGroups.size()) {
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1"));

            groupsForTest = app.db().groups();

            app.goTo().gotoHomePage();
        }

        for (GroupDate group : modifiedContactGroups) {
            groupsForTest = groupsForTest.without(group);
        }

        GroupDate groupToAddContactTo = groupsForTest.iterator().next();
        app.contact().addToGroup(contactToAddToGroup, groupToAddContactTo);
        ListOfContactsInGroup after = app.db().groupsWithContact();

        assertThat(after, equalTo(before.withAdded(new ContactInGroupData().withGroupId(groupToAddContactTo.getId()))));
    }

    @Test
    public void testDeleteContactFromGroup() throws InterruptedException {
        app.goTo().gotoHomePage();
        ListOfContactsInGroup before = app.db().groupsWithContact();

        if (before.size() == 0) {
            ContactDate contactToAddToGroup = app.db().contacts().iterator().next();
            GroupDate group = app.db().groups().iterator().next();

            app.contact().addToGroup(contactToAddToGroup, group);
            Thread.sleep(500);
            before = app.db().groupsWithContact();

            app.goTo().gotoHomePage();
        }

        ContactInGroupData toDelete = before.iterator().next();
        app.contact().removeFromGroup(toDelete);

        Thread.sleep(500);
        ListOfContactsInGroup after = app.db().groupsWithContact();

        assertThat(after, equalTo(before.without(toDelete)));
    }
}