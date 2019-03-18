import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import models.Task;
import services.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! If you want to enter, print enter.\nIf you want to add new desk, print add desk\n" +
                "new card - add card\n" +
                "new task - add task.");

        switch (scanner.nextLine()) {
            case "enter":
                UserService userService = new UserService();
                userService.entryUser();
                break;
            case "add desk":
                DesksDao desksDao = new DesksDao();
                System.out.println("Add desk");
//                desksDao.addNewDesk();
                break;
            case "add card":
                CardsDao cardsDao = new CardsDao();
                System.out.println("Add card");
//                cardsDao.addNewCard();
                break;
            case "add task":
                TasksDao tasksDao = new TasksDao();
                System.out.println("Add task");
//                tasksDao.addNewTask();
                break;
        }


    }
}
