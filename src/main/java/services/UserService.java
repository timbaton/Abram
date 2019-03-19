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

    private String command = "";
    private Scanner sc = new Scanner(System.in);
    private UsersDao usersDao = new UsersDao();
    private TasksDao tasksDao = new TasksDao();
    private DesksDao desksDao = new DesksDao();
    private CardsDao cardsDao = new CardsDao();

    public void entryUser(String login,String password) {

//  проверка аутентификации
        User userCandidate = usersDao.findByLogin(login).get(0);
        if (login.equals(userCandidate.getLogin()) && password.equals(userCandidate.getPassword())) {
            System.out.println("You logged in successfully");
        } else {
            System.out.println("Oops! Invalid data!");
        }
        showUserDesks(login);
    }

    private void showUserDesks(String login) {

//  посмотреть все столы юзера
        command = sc.nextLine();
        List<Desk> userDesks;
        if (command.equals("my desks")) {
            userDesks = desksDao.findAllUserDesks(login);
            System.out.println("Your desks: ");

            if (!userDesks.isEmpty()) {
                int deskNumber = 0;
                for (Desk desk : userDesks) {
                    deskNumber++;
                    System.out.println(deskNumber + "." + desk.getName());
                }
                showDeskCards(userDesks);
            } else
                System.out.println("User doesn't have any desks!");
        }
    }

    private void showDeskCards(List<Desk> userDesks) {

//  посмотреть карточки из данного стола
        command = sc.nextLine();
        List<Card> userCards = new ArrayList<>();
        for (Desk desk : userDesks) {
            if (command.equals(desk.getName())) {
                userCards = cardsDao.findAllCardsFromDesk(desk.getName());
                System.out.println(desk.getName() + " " + "has cards: ");

                if (!userCards.isEmpty()) {
                    int cardNumber = 0;
                    for (Card card : userCards) {
                        cardNumber++;
                        System.out.println(cardNumber + "." + card.getName());
                    }
                } else
                    System.out.println("No cards in this desk!");
            }
        }
        showCardTasks(userCards);
    }

    private void showCardTasks(List<Card> userCards) {
        command = sc.nextLine();
        List<Task> userTasks;
        for (Card card : userCards) {
            if (command.equals(card.getName())) {
                userTasks = tasksDao.findAllTasksFromCard(card.getName());
                System.out.println(card.getName() + " " + "has tasks: ");

                if (!userTasks.isEmpty()) {
                    int taskNumber = 0;
                    for (Task task : userTasks) {
                        taskNumber++;
                        System.out.println(taskNumber + "." + task.getName());
                    }
                    showTaskDescription(userTasks);
                } else
                    System.out.println("No tasks in this card!");
            }
        }
    }

    private void showTaskDescription(List<Task> userTasks) {

//  посмотреть описание таска
        System.out.println("Enter the name of task to see the description");
        command = sc.nextLine();
        for (Task task : userTasks) {
            if (command.equals(task.getName())) {
                System.out.println(tasksDao.find(task.getName()).get(0).getDescription());
            }
        }
    }
}
