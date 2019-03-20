package screens;

import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.DeskService;
import utils.ScannerFactory;

import java.util.Scanner;

public class DesksScreen {
    private static User user;
    private static Scanner scanner;
    private static DeskService deskService;

    public DesksScreen(User user) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        deskService = new DeskService();
        scanner = context.getBean(ScannerFactory.class).getSystemIn();

        DesksScreen.user = user;
    }

    public void openScreen() {
        deskService.showUsersDesks(user);

        manageEvents();
    }

    private void manageEvents() {
        System.out.println("What do you want to do?\n1)add desk    2)get back");
        switch (scanner.nextLine()) {
            case "1":
            case "add desk":
                System.out.println("Give the name to your desk");
                String deskName = scanner.nextLine();
                deskService.addNewDesk(deskName);
//                desksDao.add(deskName, login);
                break;
            case "2":
            case "quit":
                break;
            default:
                System.out.println("please, enter correct value");
                manageEvents();
                break;
        }
    }
}
