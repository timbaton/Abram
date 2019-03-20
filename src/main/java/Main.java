import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import models.User;
import screens.DesksScreen;
import screens.LoginScreen;
import services.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");


        LoginScreen loginScreen = new LoginScreen();
        loginScreen.startLogging();

    }
}
