import dao.UsersDao;
import models.User;
import services.UserService;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.entryUser();
    }
}
