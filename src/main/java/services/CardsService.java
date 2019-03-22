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

    private final CardsScreen cardsScreen;
    private final CardsDao cardsDao;
    private Scanner scanner;

    @Autowired
    public CardsService(ScannerFactory scannerFactory, CardsDao cardsDao, CardsScreen cardsScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.cardsDao = cardsDao;
        this.cardsScreen = cardsScreen;
    }

    public void addNewCard(String cardName, Desk desk) {
        cardsDao.addCard(cardName, desk.getName());
        System.out.println("added new card" + " " + cardName);
        switch (scanner.nextLine()) {
            case "1":
            case "quit":
                System.out.println("Returned to cards");
                cardsScreen.openScreen();
                break;
            default:
                System.out.println("please, enter correct value");
                break;
        }

    }
}
