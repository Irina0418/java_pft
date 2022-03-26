package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactDate {
    @Id
    @Column(name="id")
    @XStreamOmitField()
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Expose
    @Column(name="home")
    @Type(type="text")
    private String home;

    @Column(name="mobile")
    @Type(type="text")
    private String mobile;

    @Column(name="work")
    @Type(type="text")
    private String workPhone;

    @Expose
    @Column(name="email")
    @Type(type="text")
    private String email;

    @Column(name="email2")
    @Type(type="text")
    private  String email2;

    @Column(name="email3")
    @Type(type="text")
    private  String email3;

    /*@Transient
    private String group;*/

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name="photo")
    @Type(type="text")
    private String photo;

    @Column(name="phone2")
    @Type(type="text")
    private String phone2;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupDate> groups = new HashSet<GroupDate>();

    public ContactDate withPhoto(File photo) {
        this.photo = photo.getPath();
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

   /* public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }*/

    public ContactDate withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactDate withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactDate withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

     public Groups getGroups() {
        return new Groups(groups);
    }

    public String getPhone2() {
        return phone2;
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

    public void setGroups(Set<GroupDate> groups) {
        this.groups = groups;
    }

    /* public String getGroup() {
        return group;
    }*/

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
        return new File(photo);
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDate that = (ContactDate) o;
        return id == that.id
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(address, that.address)
                && Objects.equals(home, that.home)
                && Objects.equals(mobile, that.mobile)
                && Objects.equals(workPhone, that.workPhone)
                && Objects.equals(email, that.email)
                && Objects.equals(email2, that.email2)
                && Objects.equals(email3, that.email3)
                && Objects.equals(phone2, that.phone2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                firstname,
                lastname,
                address,
                home,
                mobile,
                workPhone,
                email,
                email2,
                email3,
                phone2);
    }

    public ContactDate inGroup(GroupDate group) {
        this.groups.add(group);
        return this;
    }
}