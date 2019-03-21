package screens;

import dao.CardsDao;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CardsService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class CardsScreen {

    private Scanner scanner;

    @Autowired
    private CardsDao cardsDao;
    @Autowired
    private CardsService cardsService;
    @Autowired
    private DesksScreen desksScreen;

    @Autowired
    public CardsScreen(ScannerFactory scannerFactory, DesksScreen desksScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.desksScreen = desksScreen;
    }

    public void openCards(Desk desk) {

//  посмотреть карточки из данного стола
        List<Card> userCards = cardsDao.findAllCardsFromDesk(desk.getName());
        System.out.println(desk.getName() + " " + "has cards: ");

        if (!userCards.isEmpty()) {
            int cardNumber = 0;
            for (Card card : userCards) {
                cardNumber++;
                System.out.println(cardNumber + ")" + card.getName());
            }
        } else
            System.out.println("No cards in this desk!");
        manageEvents(desk.getName());
    }

    private void manageEvents(String deskName) {
        System.out.println("What do you want to do?\n1)add card     2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add card":
                System.out.println("Give the name to your card");
                String cardName = scanner.nextLine();
                cardsService.addNewCard(cardName, deskName);
                break;
            case "2":
            case "open":
//                showCardsTasks();
                break;
            case "3":
            case "quit":
                desksScreen.manageEvents();

            default:
                System.out.println("please, enter correct value");
                manageEvents(deskName);
                break;
        }
    }
}
