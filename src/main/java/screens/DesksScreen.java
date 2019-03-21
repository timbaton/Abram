package screens;

import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DeskService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class DesksScreen {
    private Scanner scanner;
    private static List<Desk> desks;
    @Autowired
    private DeskService deskService;
    @Autowired
    private CardsScreen cardsScreen;

    @Autowired
    public DesksScreen(ScannerFactory scannerFactory) {
        scanner = scannerFactory.getSystemIn();
    }

    public void openScreen(User user) {
        desks = deskService.getDesks(user);
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
                System.out.println("Give the name to your desk");
                String deskName = scanner.nextLine();
                deskService.addNewDesk(deskName);
                break;
            case "2":
            case "open":
                showDeskCards();
                break;
            case "3":
            case "quit":
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.startLogging();
            default:
                System.out.println("please, enter correct value");
                manageEvents();
                break;
        }
    }

    //    посмотреть карточки из данного стола
    private void showDeskCards() {
        System.out.println("Выберете номер доски: ");
        for (int i = 0; i < desks.size(); i++) {
            System.out.println(i + 1 + ")" + desks.get(i).getName());
        }
        Desk openingDesk = desks.get(Integer.valueOf(scanner.nextLine()) - 1);
        cardsScreen.openCards(openingDesk);
    }
}
