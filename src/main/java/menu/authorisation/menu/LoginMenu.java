package menu.authorisation.menu;

import lombok.AllArgsConstructor;
import menu.MenuItem;
import services.connection.AuthorService;

import java.util.Scanner;

@AllArgsConstructor
public class LoginMenu implements MenuItem {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    Scanner scanner;
    AuthorService authorizingService;

    @Override
    public String getName() {
        return "LOGIN";
    }

    @Override
    public void run() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try{
            if (authorizingService.login(login, password)) {
                System.out.println(GREEN + "- You have authorised - " + RESET);
            } else {
                System.err.println("Login ERROR");
            }
        } catch (RuntimeException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public boolean closeAfter() {
        return authorizingService.isAuthoring();
    }
}

