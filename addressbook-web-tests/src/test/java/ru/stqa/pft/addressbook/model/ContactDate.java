package ru.stqa.pft.addressbook.model;

public class ContactDate {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String home;
    private final String mobile;
    private final String email;

    public ContactDate(String firstname, String lastname, String address, String home, String mobile, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.email = email;
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
}
