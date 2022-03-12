package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().gotoHomePage();
        if(app.contact().all().size() == 0){
            app.contact().create(new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
                    .withHome("8-8890-887").withMobile("89628282828").withWorkPhone("+78990").withGroup("test3"));
        }
    }


    @Test
    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
       // assertThat(contact.getAllPhones(), equalTo(cleaned(contactInfoFromEditForm.getAllPhones())));
    }

    private String mergePhones(ContactDate contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(),contact.getWorkPhone())
                .stream().filter((s -> !s.equals(""))).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
