package services;

import dao.DesksDao;
import dao.TasksDao;
import dao.UsersDao;
import models.Desk;
import models.Task;
import models.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class UserService {

    private Scanner sc;
    private UsersDao usersDao = new UsersDao();
    private TasksDao tasksDao = new TasksDao();
    private DesksDao desksDao = new DesksDao();

    //  вход для юзера
    public void entryUser() {
        sc = new Scanner(System.in);

//  приветствие, запрос логина и пароля
        System.out.println("Hello! Enter login and password");
        System.out.print("Login ");
        String login = sc.nextLine();
        System.out.print("Password ");
        String password = sc.nextLine();

//  проверка аутентификации
        User userCandidate = usersDao.findByLogin(login).get(0);
        if (login.equals(userCandidate.getLogin()) && password.equals(userCandidate.getPassword())) {
            System.out.println("You logged in successfully");
        } else {
            System.out.println("Oops! Invalid data!");
        }

        showUserInfo(login);
    }

    public void showUserInfo(String login) {

//  посмотреть все столы юзера
        String command = sc.nextLine();
        if (command.equals("my desks")) {
            List<Desk> userDesks = desksDao.findAllUserDesks(login);
            System.out.println("Your desks: ");

            int deskNumber = 0;
            for (Desk desk : userDesks) {
                deskNumber++;
                System.out.println(deskNumber + "." + desk.getName());
            }
        } else if (command.equals("my tasks")) {
            List<Task> userTasks = tasksDao.findAllUserTasks(login);
            System.out.println("Your tasks: ");

            int taskNumber = 0;
            for (Task task : userTasks) {
                taskNumber++;
                System.out.println(taskNumber + "." + task.getName());
            }
        }

    }
}
