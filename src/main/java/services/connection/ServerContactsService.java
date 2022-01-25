package services.connection;

import dto.addition.StatusAddResponse;
import dto.find.FindRequest;
import dto.find.FindResponse;
import dto.get.all.ShowAllResponse;
import models.Contact;
import services.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerContactsService implements ContactService {
    ServerService serverService = new ServerService();
    private static final String URL = "https://mag-contacts-api.herokuapp.com";

    @Override
    public List<Contact> showAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            ShowAllResponse showAllResponse =
                    serverService.makeGetRequest(URL + "/contacts",
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + ExternalAuthorService.token,
                                    "Content-Type", "application/json"),
                            ShowAllResponse.class);

            for (Contact contact : showAllResponse.getContacts()) {
                contacts.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return contacts;
    }

    @Override
    public List<Contact> findContactName(String name) {
        FindRequest findRequest = new FindRequest().setName(name);
        List<Contact> contacts = new ArrayList<>();
        try {

            FindResponse findResponse =
                    serverService.makePostRequest(URL + "/contacts/find",
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + ExternalAuthorService.token,
                                    "Content-Type", "application/json"),
                            findRequest,
                            FindResponse.class);

            for (Contact contact : findResponse.getContacts()) {
                contacts.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return contacts;
    }

    @Override
    public List<Contact> findContactValue(String start) {
        FindRequest findRequest = new FindRequest().setValue(start);
        List<Contact> contacts = new ArrayList<>();

        try {
            FindResponse findResponse =
                    serverService.makePostRequest(URL + "/contacts/find",
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + ExternalAuthorService.token,
                                    "Content-Type", "application/json"),
                            findRequest,
                            FindResponse.class);

            for (Contact contact : findResponse.getContacts()) {
                contacts.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return contacts;
    }

    @Override
    public void addContact(Contact contact) {
        try {
            StatusAddResponse statusResponse =
                    serverService.makePostRequest(URL + "/contacts/add",
                    Map.of("Accept", "application/json",
                            "Authorization", "Bearer " + ExternalAuthorService.token,
                            "Content-Type", "application/json"),
                    contact,
                    StatusAddResponse.class);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public void removeContact(int index) {
        throw new RuntimeException("That operation isn't supported by server");
    }

    @Override
    public void saveContact(List<Contact> contacts) {
        throw new RuntimeException("That operation isn't supported by server");
    }
}
