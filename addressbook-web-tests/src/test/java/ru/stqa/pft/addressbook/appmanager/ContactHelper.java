package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase{
    //private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        //this.wd=wd;
        super(wd);
    }

    public void saveAddress() {
     wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void addAddress(ContactDate contactDate) {
        type("firstname", contactDate.getFirstname());
        type("lastname", contactDate.getLastname());
        type("address", contactDate.getAddress());
        type("home", contactDate.getHome());
        type("mobile", contactDate.getMobile());
        type("email", contactDate.getEmail());
    }

    private void type(String firstname, String contactDate) {
        wd.findElement(By.name(firstname)).click();
        wd.findElement(By.name(firstname)).clear();
        wd.findElement(By.name(firstname)).sendKeys(contactDate);
    }


    public void selectContact() {
       click(By.id("10"));
    }

    public void deleteSelectedContact() {
       click(By.xpath("//input[@value='Delete']"));
    }
    public void acceptDeleteContact(){
        wd.switchTo().alert().accept();
    };

    public void editContact() {
        click(By.xpath("//tr[3]/td[8]/a/img"));
    }

    public void updateContact() {
        click(By.name("update"));
    }
}
