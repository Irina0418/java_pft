package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().gotoHomePage();
        if(app.contact().all().size() == 0){
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                    .withMobile("89628282828").withEmail("ivan@gmail.com").withGroup("test3"));
        }
    }
    @Test
    public void testContactEmails(){
        app.goTo().gotoHomePage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String s = contact.getAllEmails();
        String s1 = mergeEmails(contactInfoFromEditForm);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        // assertThat(contact.getAllPhones(), equalTo(cleaned(contactInfoFromEditForm.getAllPhones())));
    }

    private String mergeEmails(ContactDate contact) {
        String contactEmail = contact.getEmail();
        String contactEmail2 = contact.getEmail2();
        String contactEmail3 = contact.getEmail3();

       return Arrays.asList(contactEmail, contactEmail2, contactEmail3)
                .stream().filter((s -> !s.equals(""))).map(ContactMailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("\\s","").replaceAll("[-()]","");
    }

}

