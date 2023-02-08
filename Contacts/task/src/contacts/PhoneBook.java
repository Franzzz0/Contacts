package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private final List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void add (Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public Contact getContact(int index) {
        return this.contacts.get(index);
    }

    public int getRecordsNumber() {
        return this.contacts.size();
    }

    public List<Contact> search(String query) {
        List<Contact> matchedList = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getSuperstring().toLowerCase().matches(String.format(".*%s.*", query.toLowerCase()))) {
                matchedList.add(contact);
            }
        }
        return matchedList;
    }

    public void listRecords() {
        listRecords(contacts);
    }

    public void listRecords(List<Contact> list) {
        int i = 1;
        for (Contact record : list) {
            System.out.printf("%d. %s%n", i, record.getInfo());
            i++;
        }
    }
}
