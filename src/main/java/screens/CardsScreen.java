package screens;

import dao.CardsDao;
import models.Card;
import models.Desk;
import java.util.List;

public class CardsScreen {
    CardsDao cardsDao;

    public void openCards(Desk desk) {

//  посмотреть карточки из данного стола
        List<Card> userCards;
        userCards = cardsDao.findAllCardsFromDesk(desk.getName());
        System.out.println(desk.getName() + " " + "has cards: ");

        if (!userCards.isEmpty()) {
            int cardNumber = 0;
            for (Card card : userCards) {
                cardNumber++;
                System.out.println(cardNumber + "." + card.getName());
            }
        } else
            System.out.println("No cards in this desk!");
    }
}
