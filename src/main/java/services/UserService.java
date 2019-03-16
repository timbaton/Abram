package services;

import dao.UsersDao;
import models.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserService {

    private Scanner sc;
    private UsersDao dao = new UsersDao();

    // вход для юзера
    public void entryUser() {
        sc = new Scanner(System.in);
//приветствие, запрос логина и пароля

        System.out.println("Hello! Enter login and password");
        System.out.print("Login ");
        String login = sc.nextLine();
        System.out.println("Password ");
        String password = sc.nextLine();

//  проверка аутентификации
        User userCandidate = dao.findByLogin(login).get(0);
        if (login.equals(userCandidate.getLogin()) && password.equals(userCandidate.getPassword())) {
            System.out.println("You logged in successfully");
        } else {
            System.out.println("Oops! Invalid data!");
        }
    }
}
