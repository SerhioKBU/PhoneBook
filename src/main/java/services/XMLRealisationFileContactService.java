package services;

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
        if(!file.exists()){
            save(List.of());
        }
        XmlMapper xmlMapper = new XmlMapper();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file))) {
            for (Contact person: contacts) {
                xmlMapper.readValue(bufferedReader, contacts.getClass());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
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
