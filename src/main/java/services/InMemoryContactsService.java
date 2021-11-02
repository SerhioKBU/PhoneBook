package services;

import models.Contact;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContactsService implements ContactService {
    List<Contact> contacts = new ArrayList<>();

    private long getNextId(){
        int nextId = 0;
        int id;
        for (int i = 0; i < contacts.size(); i++) {
            if((id = (contacts.get(i).getId())) > nextId){
                nextId = id;
            }
        }
        return ++nextId;
    }

    @Override
    public List<Contact> showAllContacts() {
        return contacts;
    }

    @Override
    public List<Contact> findContactName(String namePart) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(namePart))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> findContactValue(String start) {
            return contacts.stream()
                    .filter(contact -> contact.getValue().startsWith(start))
                    .collect(Collectors.toList());
        }

    @Override
    public void removeContact(int index) {
        contacts.remove(index);
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(Math.toIntExact(getNextId()));
        contacts.add(contact);
    }

    @Override
    public void saveContact(List<Contact> contacts) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("ContactsBook.txt");
        for (Contact element: contacts) {
                writer.write(element + System.getProperty("line.separator"));
        }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


