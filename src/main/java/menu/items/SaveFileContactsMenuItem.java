package menu.items;

import lombok.RequiredArgsConstructor;
import menu.MenuItem;
import models.Contact;
import services.ContactService;

import java.util.List;

@RequiredArgsConstructor
public class SaveFileContactsMenuItem implements MenuItem {
    private final ContactService contactService;

    @Override
    public String getName() {
        return "Save Contacts to TXT file";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactService.showAllContacts();
        contactService.saveContact(contacts);
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
