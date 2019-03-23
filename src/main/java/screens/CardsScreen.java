package screens;

import base.BaseAbstractClass;
import base.BaseScreen;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CardsService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class CardsScreen extends BaseAbstractClass {

    private Scanner scanner;

    private Desk desk;
    private static List<Card> userCards;
    private final CardsService cardsService;
    private final TasksScreen tasksScreen;
    private BaseScreen prefScreen;

    @Autowired
    public CardsScreen(ScannerFactory scannerFactory, CardsService cardsService, TasksScreen tasksScreen) {
        tasksScreen.setPrefScreen(this);

        this.scanner = scannerFactory.getSystemIn();
        this.cardsService = cardsService;
        this.tasksScreen = tasksScreen;
    }

    public void openScreen() {
        userCards = cardsService.getCards(desk);
        manageEvents();
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    @Override
    public void manageEvents() {
        showCards();

        System.out.println("What do you want to do?\n1)add card     2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add card":
                addCard();
                break;
            case "2":
            case "open":
                openCardsTasks();
                break;
            case "3":
            case "quit":
                quit();
            default:
                System.out.println("Please, enter correct value");
                manageEvents();
                break;
        }
    }

    private void showCards() {
        if (!userCards.isEmpty()) {
            System.out.println("This desk has cards: ");

            int cardNumber = 0;
            for (Card card : userCards) {
                cardNumber++;
                System.out.println(cardNumber + ")" + card.getName());
            }
        } else
            System.out.println("No cards in this desk!");
    }

    private void addCard() {
        System.out.println("Give the name to your card");
        String cardName = scanner.nextLine();
        cardsService.addNewCard(cardName, desk);
        System.out.println("Added new card" + " " + cardName);
        manageEvents();
    }

    private void openCardsTasks() {
        System.out.println("Choose the card name: ");
        for (int i = 0; i < userCards.size(); i++) {
            System.out.println(i + 1 + ")" + userCards.get(i).getName());
        }
        int index = Integer.valueOf(scanner.nextLine()) - 1;
        if (index < userCards.size()) {
            Card openingCard = userCards.get(index);
            tasksScreen.setCard(openingCard);
            tasksScreen.openScreen();
        } else
            System.out.println("Please, enter correct value");
    }

    @Override
    public void setPrefScreen(BaseScreen prefScreen) {
        this.prefScreen = prefScreen;
    }

    @Override
    public void quit() {
        prefScreen.openScreen();
    }
}
