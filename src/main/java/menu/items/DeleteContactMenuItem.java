package menu.items;

import menu.ContactView;
import menu.MenuItem;
import services.ContactService;

public class DeleteContactMenuItem implements MenuItem {
    ContactService contactService;
    ContactView contactView;

    public DeleteContactMenuItem(ContactService contactService, ContactView contactView) {
        this.contactService = contactService;
        this.contactView = contactView;
    }

    @Override
    public String getName() {
        return "Remove Contact";
    }

    @Override
    public void run() {
        contactService.removeContact(contactView.deleteContact());
        System.out.println("------------------");
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}



