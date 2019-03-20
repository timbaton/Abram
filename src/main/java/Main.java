import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import models.User;
import screens.DesksScreen;
import services.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        User user;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! If you want to enter, print enter.\nIf you want to add\nnew desk - print add desk\n" +
                "new card - add card\n" +
                "new task - add task.");

        UserService userService = new UserService();

        //  приветствие, запрос логина и пароля
        System.out.println("Hello! Enter login and password");
        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        user = userService.entryUser(login, password);
        if (user != null) {
            DesksScreen desksScreen = new DesksScreen(user);
            desksScreen.openScreen();
        }
    }
}
