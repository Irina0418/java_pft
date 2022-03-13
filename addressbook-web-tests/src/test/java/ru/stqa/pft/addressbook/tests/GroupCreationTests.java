package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.json.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class GroupCreationTests extends TestBase{
  Logger logger = getLogger(GroupCreationTests.class);

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/group.csv"));
    String line = reader.readLine();
    while (line != null && line != "") {

      String[] split = line.split(";");
      list.add(new Object[]
              {
                      new GroupDate()
                              .withName(split[0])
                              .withHeader(split[1])
                              .withFooter(split[2])
              });
      line = reader.readLine();
    }
    return list.iterator();
  }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/group.xml"))) {
        String xml = "";
        String line = reader.readLine();
        while (line != null && line != "") {
          xml += line;
          line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupDate.class);
        xStream.allowTypes(new Class[]{ru.stqa.pft.addressbook.model.GroupDate.class});
        List<GroupDate> groups = (List<GroupDate>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
    }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/group.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null && line != "") {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupDate> groups = gson.fromJson(json, new TypeToken<List<GroupDate>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validGroupsFromXml")
  public void testGroupCreation(GroupDate group) throws Exception {
    logger.info("Start test testGroupCreation");
    app.goTo().groupPage();
    Groups before = app.group().all();
    //GroupDate group= new GroupDate().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().all();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    logger.info("Stop test testGroupCreation");
  }



  @Test (enabled = false)
  public void testBadGroupCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupDate group= new GroupDate().withName("test1'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));
  }

}

