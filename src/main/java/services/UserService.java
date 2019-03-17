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

    String command = "";
    private Scanner sc;
    private UsersDao usersDao = new UsersDao();
    private TasksDao tasksDao = new TasksDao();
    private DesksDao desksDao = new DesksDao();
    private CardsDao cardsDao = new CardsDao();

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
        showUserDesks(login);
    }

    private void showUserDesks(String login) {

//  посмотреть все столы юзера
        command = sc.nextLine();
        List<Desk> userDesks;
        if (command.equals("my desks")) {
            userDesks = desksDao.findAllUserDesks(login);
            System.out.println("Your desks: ");

            int deskNumber = 0;
            for (Desk desk : userDesks) {
                deskNumber++;
                System.out.println(deskNumber + "." + desk.getName());
            }
            showDeskCards(userDesks);
        }
    }

    private void showDeskCards(List<Desk> userdesks) {
//  посмотреть карточки из данного стола
        command = sc.nextLine();
        for (Desk desk : userdesks) {
            if (command.equals(desk.getName())) {
                List<Card> userCards = cardsDao.findAllCardsFromDesk(desk.getName());
                System.out.println(desk.getName() + " " + "has cards: ");

                int cardNumber = 0;
                for (Card card : userCards) {
                    cardNumber++;
                    System.out.println(cardNumber + "." + card.getName());
                }
            }
        }
    }
}
