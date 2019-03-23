package screens;

import base.BaseAbstractClass;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DeskService;
import services.UserService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class DesksScreen extends BaseAbstractClass {

    private Scanner scanner;
    private static List<Desk> desks;
    private static DeskService deskService;
    private CardsScreen cardsScreen;
    private UserService userService;

    @Autowired
    public DesksScreen(ScannerFactory scannerFactory, DeskService deskService, CardsScreen cardsScreen, UserService userService) {
        scanner = scannerFactory.getSystemIn();

        cardsScreen.setPrefScreen(this);

        this.deskService = deskService;
        this.userService = userService;
        this.cardsScreen = cardsScreen;

    }

    public void openScreen() {
        deskService.setUser(userService.getUser());
        desks = deskService.getDesks();

        showDesks();
        manageEvents();
    }

    private void showDesks() {
        System.out.println("Your desks: ");
        int deskNumber = 0;
        for (Desk desk : desks) {
            deskNumber++;
            System.out.println(deskNumber + ")" + desk.getName());
        }
    }

    public void manageEvents() {
        System.out.println("What do you want to do?\n1)add desk     2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add desk":
                addDesk();
                break;
            case "2":
            case "open":
                openCard();
                break;
            case "3":
            case "quit":
                quit();
                return;
            default:
                System.out.println("please, enter correct value");
                manageEvents();
                break;
        }
        manageEvents();
    }

    private void addDesk() {
        System.out.println("Give the name to your desk");
        String deskName = scanner.nextLine();
        deskService.addDesk(deskName);
        System.out.println("added new desk" + deskName);
    }

    //    посмотреть карточки из данного стола
    private void openCard() {
        System.out.println("Choose the desk name: ");
        for (int i = 0; i < desks.size(); i++) {
            System.out.println(i + 1 + ")" + desks.get(i).getName());
        }
        Desk openingDesk = desks.get(Integer.valueOf(scanner.nextLine()) - 1);
        cardsScreen.setDesk(openingDesk);

        cardsScreen.openScreen();
    }
}
