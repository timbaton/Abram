package screens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.ScannerFactory;

import java.util.Scanner;

@Component
public class RegisterScreen {
    private Scanner scanner;
    private UserService userService;
    private LoginScreen loginScreen;

    @Autowired
    public RegisterScreen(ScannerFactory scannerFactory, UserService userService, LoginScreen loginScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.userService = userService;
        this.loginScreen = loginScreen;
    }

    public void registerUser() {
        System.out.println("If you want to register, create Login and Password");

        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        userService.registerNewUser(login, password);
    }
}
