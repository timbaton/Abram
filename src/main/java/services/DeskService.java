package services;

import dao.DesksDao;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screens.DesksScreen;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

public class DeskService {
    private static DesksDao desksDao;
    private static DesksScreen desksScreen;
    private static User user;
    private static List<Desk> userDesks;

    @Autowired
    public DeskService(DesksDao desksDao, User user) {
        this.desksDao = desksDao;
        this.user = user;
    }

    public List<Desk> getDesks(User user) {
        DeskService.user = user;
        userDesks = desksDao.findAllUserDesks(user);
        if (!userDesks.isEmpty()) {
            return userDesks;
        }
        return null;
    }

    public boolean addNewDesk(String name) {
        desksDao.add(name, user);
        System.out.println("todo: added new desk" + name);
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Scanner scanner = context.getBean(ScannerFactory.class).getSystemIn();

        switch (scanner.nextLine()) {
            case "1":
            case "quit":
                desksScreen.openScreen(user);
                break;
            default:
                System.out.println("please, enter correct value");
                break;
        }
        return false;
    }
}
