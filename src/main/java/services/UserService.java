package services;

import dao.DesksDao;
import dao.UsersDao;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    private User curUser = new User();
    private UsersDao usersDao;
    private DesksDao desksDao;

    @Autowired
    public UserService(UsersDao usersDao, DesksDao desksDao) {
        this.usersDao = usersDao;
        this.desksDao = desksDao;
    }

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

    public User getUser() {
        return curUser;
    }

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
