import dao.CardsDao;
import dao.DesksDao;
import dao.TasksDao;
import services.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! If you want to enter, print enter.\nIf you want to add\nnew desk - print add desk\n" +
                "new card - add card\n" +
                "new task - add task.");

        UserService userService = new UserService();

        //  приветствие, запрос логина и пароля
        System.out.println("Hello! Enter login and password");
        System.out.print("Login ");
        String login = scanner.nextLine();
        System.out.print("Password ");
        String password = scanner.nextLine();

        switch (scanner.nextLine()) {
            case "entry":
                userService.entryUser(login, password);
            case "add desk":
                DesksDao desksDao = new DesksDao();
                System.out.println("Add desk");
                String deskName = scanner.nextLine();
                desksDao.add(deskName, login);
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
