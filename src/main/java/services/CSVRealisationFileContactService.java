package services;

import models.Contact;
import models.ContactType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVRealisationFileContactService extends AbstractFileContactService {


    public CSVRealisationFileContactService(File file) {
        super(file);
    }

    @Override
    public List<Contact> load() {
        if(!file.exists()){
            save(List.of());
        }
            List<Contact> contacts = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader
                    (new FileReader(String.valueOf(file)))) {
                contacts = bufferedReader.lines()
                        .map(l -> l.split("|"))
                        .map(c -> new Contact(
                                Integer.parseInt(c[0]),
                                c[1],
                                ContactType.valueOf(c[3]),
                                c[4]))
                        .collect(Collectors.toList());

            } catch (IOException e) {
                e.printStackTrace();
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
