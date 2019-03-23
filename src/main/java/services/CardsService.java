package services;

import dao.CardsDao;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsService {

    private final CardsDao cardsDao;

    @Autowired
    public CardsService(CardsDao cardsDao) {
        this.cardsDao = cardsDao;
    }

    public void addNewCard(String cardName, Desk desk) {
        cardsDao.addCard(cardName, desk);
    }

    public List<Card> getCards(Desk desk) {
        List<Card> allCardsFromDesk = cardsDao.findAllCardsFromDesk(desk.getName());
        return allCardsFromDesk;
    }
}
