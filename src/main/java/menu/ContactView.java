package menu;

import lombok.RequiredArgsConstructor;
import models.Contact;
import models.ContactType;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
public class ContactView {
    private final Scanner scanner;
    //List<Contact> contacts;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void writeFindNameContacts(List<Contact> contacts) {
        System.out.println("----------- FOUND NAME CONTACTS -----------");
        for (Contact c : contacts) {
            System.out.printf(
                    "%s - %s[%s] : %s\n",
                    c.getId(), c.getName(), c.getContactType().getName(), c.getValue());
        }
        System.out.println("-------------------------------------------");
    }

    public void writeContacts(List<Contact> contacts) {
        System.out.println("----------- CONTACTS -----------");
        for (Contact c : contacts) {
            System.out.printf(
                    "%s - %s[%s] : %s\n",
                    c.getId(), c.getName(), c.getContactType().getName(), c.getValue());
        }
        System.out.println("-----------------------------");
    }

    public int deleteContact() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        do {
            System.out.print("Enter contact's id which you wanna remove: ");
            System.out.print(" ");
            if (!scanner.hasNextInt()) {
                System.out.println(ANSI_RED + "Entered value isn't a number. Try again!" + ANSI_RESET);
                scanner.nextLine();
            } else {
                break;
            }
        } while (true);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice - 1;
    }

    public Contact readContact() {
        System.out.println("Choose contact's type:");
        ContactType type = null;
        while (type==null){
            ContactType[] types = ContactType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.printf("\t%d - %s\n", i+1, types[i].getName());
            }
            System.out.println("->");
            int ch = scanner.nextInt() -1;
            scanner.nextLine();
            if(ch<0 || ch> types.length){
                System.out.println("Try Again");
                continue;
            }
            type = types[ch];
        }
        System.out.println("Enter contact's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter contact: ");
        String value = scanner.nextLine();

        return new Contact()
                .setName(name)
                .setContactType(type)
                .setValue(value);
    }

}
