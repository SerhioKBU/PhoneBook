
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
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_Y = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContactService contactService;
        AuthorService authorService;

//        if (!contactService.equals(new ServerContactsService())) {
//            authorService = new InternalAuthorService("admin", "admin");
//        } else {
//            authorService = new ExternalAuthorService();
//        }

        System.out.println("Select Authorisation Type: \n " +
                "- enter " + ANSI_PURPLE + "'1' " + ANSI_RESET + "for Internal Authorisation; \n " +
                "- enter " + ANSI_PURPLE + "'2' " + ANSI_RESET + "for External Authorisation; \n" +
                "---> ");

        String menuItem = scanner.nextLine();

        switch (menuItem) {
            case "1": authorService = new InternalAuthorService("admin", "admin");
                break;
            case "2": authorService = new ExternalAuthorService();
                break;
            default:
                System.out.println("Please enter again");
                return;
        }

        List<MenuItem> menuAuthor = Arrays.asList(
                new LoginMenu(scanner, authorService),
                new RegistrationMenu(scanner, authorService)
        );

        Menu menu1 = new Menu(scanner, menuAuthor);
        menu1.makeMenu();

        System.out.println("Select SERVICE: \n" +
                "- enter " + ANSI_PURPLE + "'1' " + ANSI_RESET + "for PC Memory saving; \n" +
                "- enter " + ANSI_PURPLE + "'2' " + ANSI_RESET + "for Json Realisation File; \n" +
                "- enter " + ANSI_PURPLE + "'3' " + ANSI_RESET + "for XML Realisation File; \n" +
                "- enter " + ANSI_PURPLE + "'4' " + ANSI_RESET + "for CSV Realisation File; \n" +
                "- enter " + ANSI_PURPLE + "'5' " + ANSI_RESET + "for Bytes Realisation File; \n" +
                "- enter " + ANSI_PURPLE + "'6' " + ANSI_RESET + "for Server API connection; \n " +
                " ---> ");

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

        System.out.println(ANSI_Y +"- Choosing Service is "+
                contactService.getClass().getSimpleName() + " -" + ANSI_RESET);

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
