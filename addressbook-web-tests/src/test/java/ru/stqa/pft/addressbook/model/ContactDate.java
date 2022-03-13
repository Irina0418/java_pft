package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")

public class ContactDate {
    @XStreamOmitField()
    private  int id = Integer.MAX_VALUE;
    @Expose
    private  String firstname;
    @Expose
    private  String lastname;
    @Expose
    private  String address;
    @Expose
    private  String home;
    private  String mobile;
    private  String workPhone;
    @Expose
    private  String email;
    private  String email2;
    private  String email3;
    private String group;
    private String allPhones;
    private String allEmails;
    private File photo;



    public ContactDate withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

     public ContactDate withId(int id) {
        this.id = id;
        return this;
    }

    public ContactDate withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDate withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactDate withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactDate withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactDate withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactDate withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactDate withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactDate withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactDate withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactDate withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactDate withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }


    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public File getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDate that = (ContactDate) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }


}
