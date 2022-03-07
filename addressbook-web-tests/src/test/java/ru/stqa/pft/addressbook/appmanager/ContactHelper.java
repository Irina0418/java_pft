package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    //private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        //this.wd=wd;
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
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
        }  else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    /*private void type(String firstname, String contactDate) {
        wd.findElement(By.name(firstname)).click();
        wd.findElement(By.name(firstname)).clear();
        wd.findElement(By.name(firstname)).sendKeys(contactDate);
    }*/


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
       //click(By.name("selected[]"));
        //  click(By.id("36"));
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


    public void createContact(ContactDate contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        saveAddress();
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
        //return isElementPresent(By.id("26"));
    }

    public List<ContactDate> getContactList() {

        List<ContactDate> contacts = new ArrayList<ContactDate>();
        WebElement table = wd.findElement(By.xpath("//table[@id='maintable']"));
        List<WebElement> rows;
        try {
            rows = table.findElements(By.tagName("tr"));
        }
        catch (Exception e){
            return contacts;
        }
        //List<WebElement> rows = table.findElements(By.tagName("tr"));
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

