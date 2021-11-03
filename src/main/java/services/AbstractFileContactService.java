package services;

import models.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFileContactService implements ContactService {
    protected abstract List<Contact> load();
    protected abstract void save(List<Contact> contacts);
    final File file;
    List<Contact> contacts = new ArrayList<>();;

    public AbstractFileContactService(File file) {
        this.file = file;
        contacts = load();
    }

    private long getNextId(List<Contact> contacts){
        contacts = load();
        int nextId = 0;
        int id;
        for (int i = 0; i < contacts.size(); i++) {
            if((id = Integer.parseInt(contacts.get(i).getId())) > nextId){
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
        save(contacts);
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(String.valueOf(getNextId(contacts)));
        contacts.add(contact);
        save(contacts);
    }

    @Override
    public void saveContact(List<Contact> contacts) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("ContactBook.txt");
            for (Contact element: contacts) {
                writer.write(element + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

