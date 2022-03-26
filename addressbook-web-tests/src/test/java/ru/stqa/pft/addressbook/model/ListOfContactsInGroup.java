package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ListOfContactsInGroup extends ForwardingSet<ContactInGroupData> {
    private Set<ContactInGroupData> delegate;

    public ListOfContactsInGroup(Collection<ContactInGroupData> groups) {
        this.delegate = new HashSet<ContactInGroupData>(groups);
    }

    @Override
    protected Set<ContactInGroupData> delegate() {
        return delegate;
    }

    public ListOfContactsInGroup withAdded(ContactInGroupData group) {
        ListOfContactsInGroup groups = new ListOfContactsInGroup(this);
        groups.add(group);
        return groups;
    }

    public ListOfContactsInGroup without(ContactInGroupData group) {
        ListOfContactsInGroup groups = new ListOfContactsInGroup(this);
        groups.remove(group);
        return groups;
    }
}
