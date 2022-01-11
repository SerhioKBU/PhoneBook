package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import models.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XMLRealisationFileContactService extends AbstractFileContactService{
    public XMLRealisationFileContactService(File file) {
        super(file);
    }

    public List<Contact> load() {
        if(!file.exists()) return new ArrayList<>();

        XmlMapper xmlMapper = new XmlMapper();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file))) {
                contacts = xmlMapper.readValue(bufferedReader, new TypeReference<List<Contact>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts == null ? new ArrayList<>() : contacts;
    }


    public void save(List<Contact> contacts){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
