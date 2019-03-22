package screens;

import dao.TasksDao;
import models.Card;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import services.TasksService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

public class TasksScreen {
    private Scanner scanner;
    private List<Task> userTasks;

    @Autowired
    private TasksDao tasksDao;
    @Autowired
    private TasksService tasksService;
    @Autowired
    private CardsScreen cardsScreen;

    @Autowired
    public TasksScreen(ScannerFactory scannerFactory, CardsScreen cardsScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.cardsScreen = cardsScreen;
    }

    public void openScreen(Card openingCard) {
        userTasks = tasksDao.findAllTasksFromCard(openingCard.getName());
    }
}
