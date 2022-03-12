package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;


import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void saveAddress() {
     wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactDate contactDate, boolean creation) {
        type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("address"), contactDate.getAddress());
        type(By.name("home"), contactDate.getHome());
        type(By.name("mobile"), contactDate.getMobile());
        type(By.name("email"), contactDate.getEmail());

       if (creation) {
             if (contactDate.getGroup() == wd.findElement(By.name("new_group")).getText()) {
               new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
           } else {
               new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
           }
       }

       else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

     public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
    }

    public void deleteSelectedContact() {
       click(By.xpath("//input[@value='Delete']"));
    }
    public void acceptDeleteContact(){
        wd.switchTo().alert().accept();
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));

    }

    public void updateContact() {
        click(By.name("update"));
    }


    public void create(ContactDate contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        saveAddress();
        contactCache =null;
        gotoHomePage();

    }

    public void modify(ContactDate contact) {
        editContact();
        fillContactForm(contact, false);
        updateContact();
        contactCache =null;
        gotoHomePage();
    }

   public void delete(ContactDate contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeleteContact();
        contactCache =null;
        gotoHomePage();

    }

    private void gotoAddNew() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));

    }

    private Contacts contactCache =null;


    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        WebElement table = wd.findElement(By.xpath("//table[@id='maintable']"));
        List<WebElement> rows;
        try {
            rows = table.findElements(By.tagName("tr"));
        }
        catch (Exception e){
            return new Contacts(contactCache);
        }
        for(WebElement row:rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 0) {
                continue;
            }

            WebElement Input = cells.get(0).findElement(By.tagName("input"));
            String idInput = Input.getAttribute("value");
            int id = Integer.parseInt(idInput );
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            // ContactDate contact = new ContactDate().withId(id).withFirstname(firstName).withLastname(lastName);
            contactCache.add(new ContactDate().withId(id).withFirstname(firstName)
                    .withLastname(lastName).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }


    //исправление задания 9
    public List<ContactDate> list() {

        List<ContactDate> contacts = new ArrayList<ContactDate>();
        WebElement table = wd.findElement(By.xpath("//table[@id='maintable']"));
        List<WebElement> rows;
        try {
            rows = table.findElements(By.tagName("tr"));
        }
        catch (Exception e){
            return contacts;
        }
        for(WebElement row:rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 0) {
                continue;
            }

            WebElement Input = cells.get(0).findElement(By.tagName("input"));
            String idInput = Input.getAttribute("value");
            int id = Integer.parseInt(idInput );
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            contacts.add(new ContactDate().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactDate infoFromEditForm(ContactDate contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
     wd.navigate().back();
     return new ContactDate().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
             .withEmail(email).withEmail2(email2).withEmail3(email3)
             .withAddress(address).withHome(home).withMobile(mobile).withWorkPhone(work);
     }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List <WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}

