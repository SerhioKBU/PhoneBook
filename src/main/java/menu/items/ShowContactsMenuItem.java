package menu.items;

import menu.ContactView;
import menu.MenuItem;
import services.ContactService;

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
        contactView.writeContacts(contactService.showAllContacts());
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
