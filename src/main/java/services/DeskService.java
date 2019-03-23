package services;

import dao.DesksDao;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
//        if (!userDesks.isEmpty()) {
            return userDesks;
//        }
//        return null;
    }

    public void addDesk(String name) {
        desksDao.add(name, user);
    }
}
