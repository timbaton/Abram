package screens;

import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.ScannerFactory;

import java.util.Scanner;

@Component
public class LoginScreen {

    public void startLogging() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserService userService = context.getBean(UserService.class);
        Scanner scanner = context.getBean(ScannerFactory.class).getSystemIn();
        DesksScreen desksScreen = context.getBean(DesksScreen.class);

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
