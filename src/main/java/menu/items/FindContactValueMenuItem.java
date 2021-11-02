package menu.items;

import lombok.RequiredArgsConstructor;
import menu.ContactView;
import menu.MenuItem;
import models.Contact;
import services.ContactService;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindContactValueMenuItem implements MenuItem {
    private final ContactService contactService;
    private final ContactView contactView;
    private final Scanner scanner;

    @Override
    public String getName() {
        return "Find Contact by value";
    }

    @Override
    public void run() {
        System.out.println("Enter value's start part: ");
        String value = scanner.nextLine();
        List<Contact> contacts = contactService.findContactValue(value);
        contactView.writeFindNameContacts(contacts);
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}


