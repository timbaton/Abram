package screens;

import base.BaseAbstractClass;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DeskService;
import services.UserService;
import utils.PrintManager;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class DesksScreen extends BaseAbstractClass {

    private Scanner scanner;
    private PrintManager printManager;

    private static List<Desk> desks;
    private static DeskService deskService;
    private CardsScreen cardsScreen;
    private UserService userService;

    @Autowired
    public DesksScreen(ScannerFactory scannerFactory, DeskService deskService, CardsScreen cardsScreen, UserService userService, PrintManager printManager) {
        scanner = scannerFactory.getSystemIn();
        this.printManager = printManager;

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
        StringBuilder desksList = new StringBuilder();

        if (desks.size() != 0) {
            printManager.printInNewScreen("Your desks: ");

            for (int i = 0; i < desks.size(); i++) {
                desksList.append(i + 1).append(")").append(desks.get(i).getName());
                if (i != desks.size() - 1) {
                    desksList.append("\n");
                }
            }
            printManager.print(desksList.toString());

        } else {
            printManager.printInNewScreen("You don't have any desks!");
        }
    }

    public void manageEvents() {
        printManager.print("What do you want to do?\n1)add desk     2)open      3)exit");
        System.out.print("Answer: ");
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
                break;
            default:
                printManager.printInNewScreen("Please, enter correct value");
                manageEvents();
                break;
        }
        manageEvents();
    }

    private void addDesk() {
        printManager.printInNewScreen("Give the name to your desk");
        String deskName = scanner.nextLine();
        deskService.addDesk(deskName);
        printManager.printInNewScreen("Added new desk" + " " + "-" + " " + deskName);
        showDesks();
    }

    //    посмотреть карточки из данного стола
    private void openCard() {
        if (desks.size() > 0) {
            printManager.printInNewScreen("Choose the desk name: ");

            StringBuilder desksList = new StringBuilder();
            for (int i = 0; i < desks.size(); i++) {
                desksList.append(i + 1).append(")").append(desks.get(i).getName());
                if (i != desks.size() - 1) {
                    desksList.append("\n");
                }
            }

            printManager.print(desksList.toString());

            int index = scanner.nextInt() - 1;
            if (index < desks.size()) {
                Desk openingDesk = desks.get(index);
                cardsScreen.setDesk(openingDesk);
                cardsScreen.openScreen();
            } else
                printManager.printInNewScreen("Please, enter correct value");
        } else {
            printManager.printInNewScreen("Firstly you have to add a desk!");
            manageEvents();
        }
    }
}
