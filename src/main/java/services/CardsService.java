package services;

import dao.CardsDao;
import org.springframework.beans.factory.annotation.Autowired;
import screens.CardsScreen;
import utils.ScannerFactory;

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

    public void addNewCard(String cardName,String deskName) {
        cardsDao.addCard(cardName, deskName);
        System.out.println("added new card" + " " + cardName);

    }
}
