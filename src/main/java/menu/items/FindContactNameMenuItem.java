package menu.items;

import lombok.RequiredArgsConstructor;
import menu.ContactView;
import menu.MenuItem;
import models.Contact;
import services.ContactService;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindContactNameMenuItem implements MenuItem {
    private final ContactService contactService;
    private final ContactView contactView;
    private final Scanner scanner;

    @Override
    public String getName() {
        return "Find Contact by name";
    }

    @Override
    public void run() {
        System.out.println("Enter name's start part: ");
        String name = scanner.nextLine();
        List<Contact> contacts = contactService.findContactName(name);
        contactView.writeFindNameContacts(contacts);
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}


