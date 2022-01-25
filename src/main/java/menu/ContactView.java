package menu;

import lombok.RequiredArgsConstructor;
import models.Contact;
import models.ContactType;
import utils.EmailValidator;
import utils.PhoneValidator;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
public class ContactView {
    private final Scanner scanner;
    List<Contact> contacts;
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public void writeFindNameContacts(List<Contact> contacts) {
        System.out.println("----------- FOUND NAME CONTACTS -----------");
        if(contacts.size() == 0) {
            System.out.println("Requested data couldn't find. Try again!");
            return;
        }
        System.out.printf("%-3s %-10s %-10s %-15s%s", "id", "Name", "Type",  "Value", System.lineSeparator());
        for (Contact c : contacts) {
            System.out.printf(
                    "%s - %s[%s] : %s\n",
                    c.getId(), c.getName(), c.getContactType().getName(), c.getValue());
        }
        System.out.println("-------------------------------------------");
    }

    public void writeContacts(List<Contact> contacts) {
        System.out.println("----------- CONTACTS -----------");
        if(contacts.size() == 0) {
            System.out.println("PhoneBook is empty");
            return;
        }
        System.out.printf("%-3s %-12s %-10s %-15s%s", "id", "Name", "Type",  "Value", System.lineSeparator());
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
                System.out.println(RED + "Entered value isn't a number. Try again!" + RESET);
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
        String value;
        while (true) {
            System.out.printf("Enter %s: ", type.getName());
            value = scanner.nextLine();
            if (type == ContactType.EMAIL && EmailValidator.emailValidate(value)){
                break;
            }
            if (type == ContactType.PHONE && PhoneValidator.phoneValidate(value)){
                break;
            }
            System.out.println(RED + "Incorrect value" + RESET);
        }
        return new Contact()
                .setName(name)
                .setContactType(type)
                .setValue(value);
    }

}
