package pl.losK.console;

import pl.losK.model.User;
import pl.losK.service.UserService;

import java.util.Scanner;

/**
 * Created by m.losK on 2017-03-13.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login(scanner);

    }

    private static void login(Scanner scanner) {
        UserService userService = new UserService();
        boolean isLogin = false;
        while (!isLogin) {
            System.out.println("Login:");
            String login = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();

            User user = new User(login, password);
            isLogin = userService.authenticate(user);
            if (isLogin) {
                System.out.println("Congratulations! You are logged in.");
            } else {
                System.out.println("Sorry! Wrong credentials");
            }
        }
    }
}
