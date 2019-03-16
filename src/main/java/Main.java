import dao.UsersDao;
import models.User;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UsersDao userDao = new UsersDao();

//        List<User> allUsers = userDao.findAll();
        User allUsers = userDao.find("tim").get(0);
        System.out.println(allUsers);
    }
}
