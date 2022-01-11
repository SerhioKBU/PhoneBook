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
        if(!file.exists()) return new ArrayList<>();

        List<Contact> contacts = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            while (true){
                Contact c = (Contact) ois.readObject();
                if (c == null) {
                    break;
                }
                contacts.add(c);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void save(List<Contact> contacts) {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Contact contact : contacts) {
                object.writeObject(contact);
            }
            object.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
