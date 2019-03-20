package screens;

import dao.UsersDao;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.DeskService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

public class DesksScreen {
    private static User user;
    private static Scanner scanner;
    private static List<Desk> desks;
    private static DeskService deskService;

    @Autowired
    public DesksScreen(ScannerFactory scannerFactory, DeskService deskService) {
        scanner = scannerFactory.getSystemIn();
        DesksScreen.deskService = deskService;
    }

    public void openScreen(User user) {
        desks = deskService.getDesks(user);
        showDesks();

        manageEvents();
    }

    private void showDesks() {
        System.out.println("Your desks: ");
        int deskNumber = 0;
        for (Desk desk : desks) {
            deskNumber++;
            System.out.println(deskNumber + "." + desk.getName());
        }
    }

    private void manageEvents() {
        System.out.println("What do you want to do?\n1)add desk     2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add desk":
                System.out.println("Give the name to your desk");
                String deskName = scanner.nextLine();
                deskService.addNewDesk(deskName);
                break;
            case "2":
            case "open":
                showDeskCards();
                break;
            case "3":
            case "quit":
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.startLogging();
            default:
                System.out.println("please, enter correct value");
                manageEvents();
                break;
        }
    }

    //    посмотреть карточки из данного стола
    private void showDeskCards() {
        System.out.println("Выберете номер доски: ");
        for (int i = 0; i < desks.size(); i++) {
            System.out.println(i + 1 + ")" + desks.get(i).getName());
        }
        Desk openingDesk = desks.get(Integer.valueOf(scanner.nextLine()) - 1);



    }
//
//    public void setUser(User user) {
//        DesksScreen.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
}
