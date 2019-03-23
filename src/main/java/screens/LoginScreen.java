package screens;

import base.BaseScreen;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.PrintManager;
import utils.ScannerFactory;

import java.util.Scanner;

@Component
public class LoginScreen implements BaseScreen {

    private UserService userService;
    private Scanner scanner;
    private DesksScreen desksScreen;
    private PrintManager printManager;

    @Autowired
    public LoginScreen(UserService userService, ScannerFactory scannerFactory, DesksScreen desksScreen, PrintManager printManager) {
        this.userService = userService;
        this.scanner = scannerFactory.getSystemIn();
        this.desksScreen = desksScreen;
        this.printManager = printManager;
    }

    public void startLogging() {

        //  приветствие, запрос логина и пароля
        printManager.printInNewScreen("Hello!\nEnter Please, log in");

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.entryUser(login, password);
        if (user != null) {
            desksScreen.setPrefScreen(this);
            desksScreen.openScreen();
        } else {
            System.out.println("Please, try again");
            startLogging();
        }
    }

    @Override
    public void openScreen() {
        startLogging();
    }

    @Override
    public void quit() {
        startLogging();
    }

    @Override
    public void manageEvents() {
        startLogging();
    }
}
