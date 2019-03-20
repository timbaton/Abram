package screens;

import models.User;
import services.UserService;

import java.util.Scanner;

public class LoginScreen {
    static UserService userService;

    public void startLogging() {

        userService = new UserService();

        Scanner scanner = new Scanner(System.in);

        //  приветствие, запрос логина и пароля
        System.out.println("Enter login and password");

        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        User user = userService.entryUser(login, password);
        if (user != null) {
            DesksScreen desksScreen = new DesksScreen(user);
            desksScreen.openScreen();
        } else {
            System.out.println("Please, try again");
            startLogging();
        }
    }
}
