package services;

import com.fasterxml.jackson.core.type.TypeReference;
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
        public List<Contact> load() {
            if (!file.exists()) return new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper();

            try (BufferedReader bufferedReader = new BufferedReader
                    (new FileReader(file))) {
                    contacts = objectMapper.readValue(bufferedReader, new TypeReference<List<Contact>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contacts == null ? new ArrayList<>() : contacts;
        }


        public void save(List < Contact > contacts) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
