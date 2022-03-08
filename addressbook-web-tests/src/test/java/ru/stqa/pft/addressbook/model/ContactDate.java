package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactDate {
    private  int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String home;
    private final String mobile;
    private final String email;
    private String group;

    public ContactDate(int id, String firstname, String lastname, String address, String home, String mobile, String email, String group) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactDate(String firstname, String lastname, String address, String home, String mobile, String email, String group) {
        this.id=Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
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

    public void setId(int id) {
        this.id = id;
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

    public ContactDate withId(int id) {
        this.id = id;
        return this;}
}
