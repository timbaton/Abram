import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import models.User;
import screens.DesksScreen;
import services.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class Main {
    static UserService userService;

    public static void main(String[] args) {
        System.out.println("Hello!");

        userService = new UserService();

        startLogin();

    }

    private static void startLogin() {

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
            startLogin();
        }
    }
}
