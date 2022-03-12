package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;


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
        gotoHomePage();

    }

    public void modify(ContactDate contact) {
        editContact();
        fillContactForm(contact, false);
        updateContact();
        gotoHomePage();
    }

   public void delete(ContactDate contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeleteContact();
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




    public Contacts all() {
        Contacts contacts = new Contacts();
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
            ContactDate contact = new ContactDate (id, firstName, lastName, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
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
            ContactDate contact = new ContactDate (id, firstName, lastName, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }


}

