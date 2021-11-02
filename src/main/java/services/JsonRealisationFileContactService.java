package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonRealisationFileContactService extends AbstractFileContactService {
    public JsonRealisationFileContactService(File file) {
        super(file);
    }

        @Override
        public List<Contact> load () {
            if (!file.exists()) {
                save(List.of());
            }
            List<Contact> contacts = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            try (BufferedReader bufferedReader = new BufferedReader
                    (new FileReader(file))) {
                for (Contact person : contacts) {
                    objectMapper.readValue(bufferedReader, Contact.class);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contacts;
        }


        public void save (List < Contact > contacts) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
