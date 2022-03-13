package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.csv"));
    String line = reader.readLine();
    while (line != null && line != "") {

      String[] split = line.split(";");
      list.add(new Object[]
              {
                      new ContactDate()
                              .withFirstname(split[0])
                              .withLastname(split[1])
                              .withAddress(split[2])
                              .withHome((split[3]))
                              .withEmail((split[4]))
              });
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null && line != "") {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactDate.class);
      xStream.allowTypes(new Class[]{ru.stqa.pft.addressbook.model.ContactDate.class});
      List<ContactDate> contacts = (List<ContactDate>) xStream.fromXML(xml);

      return contacts
              .stream()
              .map((c) -> new Object[]{c})
              .collect(Collectors.toList())
              .iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null && line != "") {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactDate> contacts = gson.fromJson(json, new TypeToken<List<ContactDate>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("test1"));

    }
  }


  @Test (dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactDate contact) throws Exception {
    Thread.sleep(2000);
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
   // File photo = new File("src/test/resources/photo.jpg");
    //ContactDate contact = new ContactDate().withFirstname("Ivan").withLastname("Ivanov").withAddress("Kazan")
     //       .withMobile("89628282828").withEmail("ivan@gmail.com").withGroup("test3");
    app.goTo().gotoAddNew();
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  }



