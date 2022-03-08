package ru.stqa.pft.addressbook.model;

import org.testng.Assert;

import java.util.HashSet;
import java.util.Objects;

public class GroupDate {
    private  int id =Integer.MAX_VALUE;
    private  String name;
    private  String header;
    private  String footer;


   /* public GroupDate(int id, String name, String header, String footer) {
        this.id=id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupDate(String name, String header, String footer) {
        this.id=Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }*/



    public int getId() {return id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDate groupDate = (GroupDate) o;
        return id == groupDate.id && Objects.equals(name, groupDate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public GroupDate withId(int id) {
        this.id = id;
        return this;
    }

    public GroupDate withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDate withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupDate withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    @Override
    public String toString() {
        return "GroupDate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}


