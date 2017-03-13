package pl.losK.console;

import pl.losK.model.User;
import pl.losK.service.DataService;
import pl.losK.service.UserService;

import java.util.Scanner;

/**
 * Created by m.losK on 2017-03-13.
 */
public class Main {
    public static void main(String[] args) {
//        DataService dataService = new DataService();
//        dataService.saveData();

    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        User user = new User(login, password);
        UserService userService = new UserService();
        if (userService.authenticate(user)) {
            System.out.println("Congratulations! You are logged in.");
        } else {
            System.out.println("Sorry! Wrong credentials");
        }
    }
}
