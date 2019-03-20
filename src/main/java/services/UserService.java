package services;

import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import dao.UsersDao;
import models.Card;
import models.Desk;
import models.Task;
import models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserService {
    User curUser = new User();
    private UsersDao usersDao = new UsersDao();
    private DesksDao desksDao = new DesksDao();

    public User entryUser(String login, String password) {

//  проверка аутентификации
        List<User> suitedUsers = usersDao.findByLogin(login);

        //если пользователей с таким логином нет - возвращаем null
        if (suitedUsers.size() != 0) {
            curUser = suitedUsers.get(0);
        } else {
            return null;
        }

        //если пароль не совпадает - возвращаем null
        if (password.equals(curUser.getPassword())) {
            System.out.println("You logged in successfully");
            setUsersDesks(curUser);
            return curUser;
        }
        return null;
    }

    private void setUsersDesks(User user) {
        //  посмотреть все столы юзера
        List<Desk> userDesks;
        userDesks = desksDao.findAllUserDesks(user);

        user.setOwnDesks(userDesks);
    }


    //    private void showDeskCards(List<Desk> userDesks) {
//
////
//    private void showCardTasks(List<Card> userCards) {
//        command = sc.nextLine();
//        List<Task> userTasks;
//        for (Card card : userCards) {
//            if (command.equals(card.getName())) {
//                userTasks = tasksDao.findAllTasksFromCard(card.getName());
//                System.out.println(card.getName() + " " + "has tasks: ");
//
//                if (!userTasks.isEmpty()) {
//                    int taskNumber = 0;
//                    for (Task task : userTasks) {
//                        taskNumber++;
//                        System.out.println(taskNumber + "." + task.getName());
//                    }
//                    showTaskDescription(userTasks);
//                } else
//                    System.out.println("No tasks in this card!");
//            }
//        }
//    }

//    private void showTaskDescription(List<Task> userTasks) {
//
////  посмотреть описание таска
//        System.out.println("Enter the name of task to see the description");
//        command = sc.nextLine();
//        for (Task task : userTasks) {
//            if (command.equals(task.getName())) {
//                System.out.println(tasksDao.find(task.getName()).get(0).getDescription());
//            }
//        }
//    }
}
