package screens;

import dao.CardsDao;
import models.Card;
import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CardsService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class CardsScreen {

    private Scanner scanner;

    private static List<Card> userCards;
    @Autowired
    private CardsDao cardsDao;
    @Autowired
    private CardsService cardsService;
    @Autowired
    private DesksScreen desksScreen;
    @Autowired
    private TasksScreen tasksScreen;

    @Autowired
    public CardsScreen(ScannerFactory scannerFactory, DesksScreen desksScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.desksScreen = desksScreen;
    }

    public void openScreen(Desk desk) {
        userCards = cardsDao.findAllCardsFromDesk(desk.getName());
        showCards();
        manageEvents(desk);
    }

    public void showCards() {

//  посмотреть карточки из данного стола

//        System.out.println(desk.getName() + " " + "has cards: ");
        System.out.println("This desk has cards: ");

        if (!userCards.isEmpty()) {
            int cardNumber = 0;
            for (Card card : userCards) {
                cardNumber++;
                System.out.println(cardNumber + ")" + card.getName());
            }
        } else
            System.out.println("No cards in this desk!");
    }

    private void manageEvents(Desk desk) {
        System.out.println("What do you want to do?\n1)add card     2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add card":
                System.out.println("Give the name to your card");
                String cardName = scanner.nextLine();
                cardsService.addNewCard(cardName, desk);
                break;
            case "2":
            case "open":
                showCardsTasks();
                break;
            case "3":
            case "quit":
                desksScreen.manageEvents();

            default:
                System.out.println("please, enter correct value");
                manageEvents(desk);
                break;
        }
    }

    private void showCardsTasks() {
        System.out.println("Choose the task name: ");
        for (int i = 0; i < userCards.size(); i++) {
            System.out.println(i + 1 + ")" + userCards.get(i).getName());
        }
        Card openingCard = userCards.get(Integer.valueOf(scanner.nextLine()) - 1);
        tasksScreen.openScreen(openingCard);
    }
}
