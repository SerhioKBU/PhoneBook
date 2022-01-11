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
        contacts = null;
        if (!file.exists()) {
            save(List.of());

            try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
                BufferedReader br = new BufferedReader(fr);
                contacts = br.lines()
                        .map(c -> c.split(","))
                        .map(arr -> new Contact()
                                .setId(String.valueOf(Long.parseLong(arr[0])))
                                .setName(arr[1])
                                .setContactType(ContactType.valueOf(arr[2]))
                                .setValue(arr[3])
                        ).collect(Collectors.toList());

//                for (String[] c : list) {
//                    contacts.add(new Contact(
//                            c[0],
//                            c[1],
//                            ContactType.valueOf(c[2]),
//                            c[3])
//                    );
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }

    @Override
    public void save(List<Contact> contacts){
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < contacts.size(); i++) {
                writer.write(contacts.get(i).getId()
                        + ","
                        + contacts.get(i).getName()
                        + ","
                        + contacts.get(i).getContactType()
                        + ","
                        + contacts.get(i).getValue()
                        + System.lineSeparator());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
