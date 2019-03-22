package services;

import dao.CardsDao;
import models.Card;
import models.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import screens.CardsScreen;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

public class CardsService {

    @Autowired
    private CardsScreen cardsScreen;
    @Autowired
    private CardsDao cardsDao;
    private Scanner scanner;

    @Autowired
    public CardsService(ScannerFactory scannerFactory) {
        this.scanner = scannerFactory.getSystemIn();
    }

    public void addNewCard(String cardName, Desk desk) {
        cardsDao.addCard(cardName, desk.getName());
        System.out.println("Added new card" + " " + cardName);
        switch (scanner.nextLine()) {
            case "1":
            case "quit":
                System.out.println("Returned to cards");
                cardsScreen.openScreen(desk);
                break;
            default:
                System.out.println("Please, enter correct value");
                break;
        }

    }
}
