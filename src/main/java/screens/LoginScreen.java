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
    private RegisterScreen registerScreen;

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
       manageEvents();
    }

    @Override
    public void quit() {
       manageEvents();
    }

    @Override
    public void manageEvents() {
        printManager.printInNewScreen("What do you want to do?\n1)login     2)register      3)exit");
        System.out.print("Answer: ");
        switch (scanner.nextLine()) {
            case "1":
            case "login":
                startLogging();
                break;
            case "2":
            case "register":
                registerScreen.registerUser();
                break;
            case "3":
            case "quit":
                quit();
                break;
            default:
                printManager.printInNewScreen("Please, enter correct value");
                manageEvents();
                break;
        }
        manageEvents();
    }
}
