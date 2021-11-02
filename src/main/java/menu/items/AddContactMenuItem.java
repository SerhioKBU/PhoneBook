package menu.items;


import menu.ContactView;
import menu.MenuItem;
import models.Contact;
import services.ContactService;

import java.util.Scanner;

public class AddContactMenuItem implements MenuItem {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    Scanner scanner;
    ContactService contactService;
    ContactView contactView;

    public AddContactMenuItem(Scanner scanner, ContactService contactService, ContactView contactView) {
        this.scanner = scanner;
        this.contactService = contactService;
        this.contactView = contactView;
    }

    @Override
    public String getName() {
        return "Add New Contact";
    }

    @Override
    public void run() {
        Contact contact = contactView.readContact();
        contactService.addContact(contact);
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
