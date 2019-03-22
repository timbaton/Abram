package screens;

import base.BaseScreen;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.ScannerFactory;

import java.util.Scanner;

@Component
public class LoginScreen implements BaseScreen {

    private UserService userService;
    private Scanner scanner;
    private DesksScreen desksScreen;

    @Autowired
    public LoginScreen(UserService userService, ScannerFactory scannerFactory, DesksScreen desksScreen) {
        this.userService = userService;
        this.scanner = scannerFactory.getSystemIn();
        this.desksScreen = desksScreen;
    }

    public void startLogging() {

        //  приветствие, запрос логина и пароля
        System.out.println("Enter login and password");

        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        User user = userService.entryUser(login, password);
        if (user != null) {
            desksScreen.openScreen();
        } else {
            System.out.println("Please, try again");
            startLogging();
        }
    }

    @Override
    public void manageEvents() {
    }

    @Override
    public void openScreen() {

    }

    @Override
    public void quit() {
        startLogging();
    }
}
