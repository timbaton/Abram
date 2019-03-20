package screens;

import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.UserService;
import utils.ScannerFactory;

import java.util.Scanner;

public class LoginScreen {
    private static UserService userService;
    private static DesksScreen desksScreen;
    private static Scanner scanner;

    public void startLogging() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        userService = context.getBean(UserService.class);
        scanner = context.getBean(ScannerFactory.class).getSystemIn();
        desksScreen = context.getBean(DesksScreen.class);

        //  приветствие, запрос логина и пароля
        System.out.println("Enter login and password");

        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        User user = userService.entryUser(login, password);
        if (user != null) {
            desksScreen.openScreen(user);
        } else {
            System.out.println("Please, try again");
            startLogging();
        }
    }
}
