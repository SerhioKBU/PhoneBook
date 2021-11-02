package menu.items;

import menu.ContactView;
import menu.MenuItem;
import models.Contact;
import services.ContactService;

import java.util.List;

public class ShowContactsMenuItem implements MenuItem {
    ContactService contactService;
    ContactView contactView;

    public ShowContactsMenuItem(ContactService contactService, ContactView contactView) {
        this.contactService = contactService;
        this.contactView = contactView;
    }

    @Override
    public String getName() {
        return "Show Contacts List";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactService.showAllContacts();
        contactView.writeContacts(contacts);
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
