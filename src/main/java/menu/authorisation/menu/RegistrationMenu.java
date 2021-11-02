package menu.authorisation.menu;

import lombok.AllArgsConstructor;
import menu.MenuItem;
import services.connection.AuthorService;

import java.util.Scanner;

@AllArgsConstructor
public class RegistrationMenu implements MenuItem {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    Scanner scanner;
    AuthorService authorizingService;

    @Override
    public String getName() {
        return "REGISTRATION";
    }

    @Override
    public void run() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter birthday date (yyyy-MM-dd): ");
        String birthDay = scanner.nextLine();
        try {
            if (authorizingService.registration(login, password, birthDay)) {
                System.out.println(ANSI_GREEN + "- You have authorised - " + ANSI_RESET);
            } else {
                System.err.println("Registration ERROR");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("ERROR!: " + e.getMessage());
        }
    }

    @Override
    public boolean closeAfter() {
        return authorizingService.isAuthoring();
    }
}
