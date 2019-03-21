package screens;

import dao.CardsDao;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsScreen {

    @Autowired
    private CardsDao cardsDao;

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
