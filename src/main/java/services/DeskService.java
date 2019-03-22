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

    private User user;
    private final DesksDao desksDao;

    @Autowired
    public DeskService(DesksDao desksDao) {
        this.desksDao = desksDao;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Desk> getDesks() {
        List<Desk> userDesks = desksDao.findAllUserDesks(user);
        if (!userDesks.isEmpty()) {
            return userDesks;
        }
        return null;
    }

    public void addNewDesk(String name) {
        desksDao.add(name, user);
    }
}
