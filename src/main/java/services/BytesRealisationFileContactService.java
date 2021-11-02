package services;

import lombok.SneakyThrows;
import models.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BytesRealisationFileContactService extends AbstractFileContactService {
    public BytesRealisationFileContactService(File file) {
        super(file);
    }

    @SneakyThrows
    public List<Contact> load() {
        if(!file.exists()){
            save(List.of());
        }
        List<Contact> contacts = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            for (int i = 0; i < contacts.size(); i++) {
                Contact c = (Contact) ois.readObject();
                contacts.add(c);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void save(List<Contact> contacts) {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < contacts.size(); i++) {
                object.writeObject(contacts.get(i));
            }
            object.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
