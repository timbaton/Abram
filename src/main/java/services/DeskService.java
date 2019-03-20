package services;

import dao.DesksDao;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import screens.DesksScreen;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class DeskService {
    private static User user;
    private static Scanner scanner;
    private static DesksDao desksDao;
    private static DesksScreen desksScreen;

    @Autowired
    public DeskService(DesksDao desksDao, ScannerFactory scannerFactory) {
        scanner = scannerFactory.getSystemIn();
        DeskService.desksDao = desksDao;
    }

    public List<Desk> getDesks(User user) {
        DeskService.user = user;
        List<Desk> userDesks = desksDao.findAllUserDesks(user);
        if (!userDesks.isEmpty()) {
            return userDesks;
        }
        return null;
    }

    public void addNewDesk(String name) {
        desksDao.add(name, user);
        System.out.println("todo: added new desk" + name);

        switch (scanner.nextLine()) {
            case "1":
            case "quit":
                desksScreen.openScreen(user);
                break;
            default:
                System.out.println("please, enter correct value");
                break;
        }
    }
}
