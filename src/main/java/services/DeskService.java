package services;

import dao.DesksDao;
import models.Desk;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screens.DesksScreen;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

public class DeskService {
    private static DesksDao desksDao;
    private static User user;
    private static List<Desk> userDesks;

    public DeskService() {
//        ApplicationContext context = ApplicationContextSingleton.getInstance();
        desksDao = new DesksDao();
    }

    public void showUsersDesks(User user) {
        this.user = user;
        userDesks = desksDao.findAllUserDesks(user);
        if (!userDesks.isEmpty()) {
            System.out.println("Your desks: ");
            int deskNumber = 0;
            for (Desk desk : userDesks) {
                deskNumber++;
                System.out.println(deskNumber + "." + desk.getName());
            }
        } else
            System.out.println(user.getName() + " doesn't have any desks!");
    }

    public boolean addNewDesk(String name) {
        System.out.println("todo: added new desk" + name);
//        TODO: fix this shieeeet
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Scanner scanner = context.getBean(ScannerFactory.class).getSystemIn();

        switch (scanner.nextLine()) {
            case "1":
            case "quit":
                DesksScreen desksScreen = new DesksScreen(user);
                desksScreen.openScreen();
                break;
            default:
                System.out.println("please, enter correct value");
                break;
        }
        return false;
    }
}
