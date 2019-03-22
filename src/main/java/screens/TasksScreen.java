package screens;

import dao.TasksDao;
import models.Card;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TasksService;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class TasksScreen {
    private Scanner scanner;
    private List<Task> userTasks;

    private final TasksDao tasksDao;
    private final TasksService tasksService;
    private final CardsScreen cardsScreen;

    @Autowired
    public TasksScreen(ScannerFactory scannerFactory, TasksDao tasksDao, TasksService tasksService, CardsScreen cardsScreen) {
        this.scanner = scannerFactory.getSystemIn();
        this.cardsScreen = cardsScreen;
        this.tasksDao = tasksDao;
        this.tasksService = tasksService;
    }

    public void openScreen(Card openingCard) {
        userTasks = tasksDao.findAllTasksFromCard(openingCard.getName());
        showTasks();
        manageEvents();
    }

    private void manageEvents() {
        System.out.println("manage events");
    }

    private void showTasks() {
        System.out.println("This card has tasks: ");
        if (!userTasks.isEmpty()) {
            int taskNumber = 0;
            for (Task task : userTasks) {
                taskNumber++;
                System.out.println(taskNumber + ")" + task.getName());
            }
        } else
            System.out.println("No tasks in this card!");
    }
}
