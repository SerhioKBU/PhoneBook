
import menu.ContactView;
import menu.Menu;
import menu.MenuItem;
import menu.authorisation.menu.LoginMenu;
import menu.authorisation.menu.RegistrationMenu;
import menu.items.*;
import services.*;
import services.connection.AuthorService;
import services.connection.ExternalAuthorService;
import services.connection.InternalAuthorService;
import services.connection.ServerContactsService;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactService contactService;
        AuthorService authorService;

        System.out.println("MENU SERVICES: \n" +
                "- enter " + PURPLE + "'1' " + RESET + "for PC Memory saving; \n" +
                "- enter " + PURPLE + "'2' " + RESET + "for Json Realisation File; \n" +
                "- enter " + PURPLE + "'3' " + RESET + "for XML Realisation File; \n" +
                "- enter " + PURPLE + "'4' " + RESET + "for CSV Realisation File; \n" +
                "- enter " + PURPLE + "'5' " + RESET + "for Bytes Realisation File; \n" +
                "- enter " + PURPLE + "'6' " + RESET + "for Server API connection;");
        System.out.print("Select your service: ");

        String menuItems = scanner.nextLine();
        switch (menuItems){
            case "1": contactService = new InMemoryContactsService();
                break;
            case "2": contactService = new JsonRealisationFileContactService
                    (new File("ContactsBook.json"));
                break;
            case "3": contactService = new XMLRealisationFileContactService
                    (new File("ContactsBook.xml"));
                break;
            case "4": contactService = new CSVRealisationFileContactService
                    (new File("ContactsBook.csv"));
                break;
            case "5": contactService = new BytesRealisationFileContactService
                    (new File("ContactsBook.obj"));
                break;
            case "6": contactService = new ServerContactsService();
                break;
            default:
                System.err.println("Please enter again");
                return;
        }

        System.out.println(YELLOW +"- Selected Service is "+
                contactService.getClass().getSimpleName() + " -" + RESET);

        if (contactService.getClass().getSimpleName().equals("ServerContactsService")) {
            authorService = new ExternalAuthorService();
        } else {
            authorService = new InternalAuthorService("admin", "admin");
        }

        System.out.println(PURPLE + "- Selected Authorisation Service is "
                + authorService.getClass().getSimpleName() + " -" + RESET);

        if (authorService.getClass().getSimpleName().equals("ExternalAuthorService")) {
            System.out.println(CYAN +"- Enter your LOGIN or create new account using REGISTRATION"+ RESET);
        } else {
            System.out.println(CYAN +"- Enter your LOGIN (REGISTRATION options unsupported!))"+ RESET);
        }


        List<MenuItem> menuAuthor = Arrays.asList(
                new LoginMenu(scanner, authorService),
                new RegistrationMenu(scanner, authorService)
        );

        Menu menu1 = new Menu(scanner, menuAuthor);
        menu1.makeMenu();

        if (authorService.isAuthoring()) {

            ContactView contactView = new ContactView(scanner);
            List<MenuItem> menuItemList = Arrays.asList(
                    new AddContactMenuItem(scanner, contactService, contactView),
                    new ShowContactsMenuItem(contactService, contactView),
                    new DeleteContactMenuItem(contactService, contactView),
                    new FindContactNameMenuItem(contactService, contactView, scanner),
                    new FindContactValueMenuItem(contactService, contactView, scanner),
                    new SaveFileContactsMenuItem(contactService)
            );
            Menu menu3 = new Menu(scanner, menuItemList);
            menu3.makeMenu();
        }
    }
}
