package screens;

import base.BaseAbstractClass;
import base.BaseScreen;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CardsService;
import utils.PrintManager;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class CardsScreen extends BaseAbstractClass {

    private Scanner scanner;

    private Desk desk;
    private static List<Card> userCards;
    private CardsService cardsService;
    private TasksScreen tasksScreen;
    private BaseScreen prefScreen;
    private PrintManager printManager;

    @Autowired
    public CardsScreen(ScannerFactory scannerFactory, CardsService cardsService, TasksScreen tasksScreen, PrintManager printManager) {
        tasksScreen.setPrefScreen(this);

        this.scanner = scannerFactory.getSystemIn();
        this.cardsService = cardsService;
        this.tasksScreen = tasksScreen;
        this.printManager = printManager;
    }

    public void openScreen() {
        userCards = cardsService.getCards(desk);

        showCards();
        manageEvents();
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    @Override
    public void manageEvents() {
        printManager.print("What do you want to do?\n1)add card     2)open      3)exit");
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
                break;
            default:
                System.out.println("Please, enter correct value");
                manageEvents();
                break;
        }
    }

    private void showCards() {
        StringBuilder allCards = new StringBuilder();

        if (!userCards.isEmpty()) {
            printManager.printInNewScreen("This desk has cards:");

            for (int i = 0; i < userCards.size(); i++) {
                allCards.append(i + 1).append(")").append(userCards.get(i).getName());
                if (i != userCards.size() - 1) {
                    allCards.append("\n");
                }
            }
            printManager.printInNewScreen(allCards.toString());

        } else {
            printManager.printInNewScreen("No cards in this desk!");
        }
    }

    private void addCard() {
        printManager.printInNewScreen("Give the name to your card");
        String cardName = scanner.nextLine();
        cardsService.addNewCard(cardName, desk);
        printManager.printInNewScreen("Added new card" + " " + cardName);
        manageEvents();
    }

    private void openCardsTasks() {
        if (!userCards.isEmpty()) {
            StringBuilder allCards = new StringBuilder();
            printManager.printInNewScreen("Choose the card name: ");

            if (!userCards.isEmpty()) {
                allCards.append("This desk has cards:\n");

                for (int i = 0; i < userCards.size(); i++) {
                    allCards.append(i + 1).append(")").append(userCards.get(i).getName());
                    if (i != userCards.size() - 1) {
                        allCards.append("\n");
                    }
                }
                printManager.print(allCards.toString());

                int index = Integer.valueOf(scanner.nextLine()) - 1;
                if (index < userCards.size()) {
                    Card openingCard = userCards.get(index);
                    tasksScreen.setCard(openingCard);
                    tasksScreen.setPrefScreen(this);
                    tasksScreen.openScreen();
                } else {
                    printManager.printInNewScreen("Please, enter correct value");
                    openCardsTasks();
                }
            }
        } else {
            printManager.printInNewScreen("Firstly you have to add a card!");
            manageEvents();
        }
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
