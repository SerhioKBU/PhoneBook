package services;
import models.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> showAllContacts();
    List<Contact> findContactName(String name);
    List<Contact> findContactValue(String start);
    void removeContact(int index);
    void addContact(Contact contact);
    void saveContact(List<Contact> contacts);
}
