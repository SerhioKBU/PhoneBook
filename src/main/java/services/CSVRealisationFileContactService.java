package services;

import models.Contact;
import models.ContactType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVRealisationFileContactService extends AbstractFileContactService {

    public CSVRealisationFileContactService(File file) {
        super(file);
    }

    @Override
    public List<Contact> load() {
        if (!file.exists()) return new ArrayList<>();

            try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
                BufferedReader br = new BufferedReader(fr);
                contacts = br.lines()
                        .map(c -> c.split(","))
                        .filter(a -> a.length==4)
                        .map(arr -> new Contact()
                                .setId((arr[0]))
                                .setName(arr[1])
                                .setContactType(ContactType.valueOf(arr[2]))
                                .setValue(arr[3])
                        ).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return contacts == null ? new ArrayList<>() : contacts;
    }

    @Override
    public void save(List<Contact> contacts){
        try {
            FileWriter writer = new FileWriter(file);
            for (Contact contact : contacts) {
                writer.write(contact.getId()
                        + ","
                        + contact.getName()
                        + ","
                        + contact.getContactType()
                        + ","
                        + contact.getValue()
                        + System.lineSeparator());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
