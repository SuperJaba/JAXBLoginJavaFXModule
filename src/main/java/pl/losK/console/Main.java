package pl.losK.console;

import pl.losK.model.User;
import pl.losK.service.DataService;
import pl.losK.service.UserService;
import pl.losK.xml.XMLFactory;

import java.util.Scanner;

/**
 * Created by m.losK on 2017-03-13.
 */
public class Main {
    public static void main(String[] args) {

        /**
         * Example of file creation with (one) xml credentials
         */

//        User user = new User("user123", "abc123");
//        XMLFactory<User> userXMLFactory = new XMLFactory<>(User.class);
//        String xmlUser = userXMLFactory.objectToXml(user);
//        DataService dataService = new DataService();
//        dataService.saveData(xmlUser);

        /**
         * Call login module
         */

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
                System.out.println("Sorry! Wrong data");
            }
        }
    }
}
