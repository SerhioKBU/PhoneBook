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
        if (!file.exists()) {
            save(List.of());
            try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
                BufferedReader br = new BufferedReader(fr);
                List<String[]> list = br.lines()
                        .map(str -> str.split(","))
                        .collect(Collectors.toList());
                for (String[] c : list) {
                    contacts.add(new Contact(
                            c[0],
                            c[1],
                            ContactType.valueOf(c[2]),
                            c[3])
                    );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }

    @Override
    public void save(List<Contact> contacts){
        try {
            FileWriter writer = new FileWriter(String.valueOf(file));
            for (Contact element: contacts) {
                writer.write(element + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
