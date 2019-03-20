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
        } else {
            System.out.println("Oops! Invalid data!");
            return null;
        }
    }

    //User Service - сервис user'a. Он отвечает за всё, что связано с юзером, за отоборажение он отвечать не должен.
//раньше тут был метод private void showUsersDesks(String login), что неправильно.
    private void setUsersDesks(User user) {

//  посмотреть все столы юзера
//        command = sc.nextLine(); -- зачем?
        List<Desk> userDesks;
//        if (command.equals("my desks")) {  -- зачем?
        //раньше ты в даошки кидала логин юзера, а там из бд опять вытаскивала его. Тоже лишняя работа
        userDesks = desksDao.findAllUserDesks(user);

        //в этом методе достаточно только установить для пользователя его доски.
        user.setOwnDesks(userDesks);
    }


    //    private void showDeskCards(List<Desk> userDesks) {
//
////  посмотреть карточки из данного стола
//        command = sc.nextLine();
//        List<Card> userCards = new ArrayList<>();
//        for (Desk desk : userDesks) {
//            if (command.equals(desk.getName())) {
//                userCards = cardsDao.findAllCardsFromDesk(desk.getName());
//                System.out.println(desk.getName() + " " + "has cards: ");
//
//                if (!userCards.isEmpty()) {
//                    int cardNumber = 0;
//                    for (Card card : userCards) {
//                        cardNumber++;
//                        System.out.println(cardNumber + "." + card.getName());
//                    }
//                } else
//                    System.out.println("No cards in this desk!");
//            }
//        }
//        showCardTasks(userCards);
//    }
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
