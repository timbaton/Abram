package services;

import dao.CardsDao;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import screens.CardsScreen;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class CardsService {

    private final CardsDao cardsDao;

    @Autowired
    public CardsService(CardsDao cardsDao) {
        this.cardsDao = cardsDao;
    }

    public void addNewCard(String cardName, Desk desk) {
        cardsDao.addCard(cardName, desk.getName());
//        System.out.println("Added new card" + " " + cardName);
//        switch (scanner.nextLine()) {
//            case "1":
//            case "quit":
//                System.out.println("Returned to cards");
//                cardsScreen.openScreen();
//                break;
//            default:
//                System.out.println("Please, enter correct value");
//                break;
//        }

    }
}
