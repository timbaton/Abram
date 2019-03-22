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
        manageEvents(openingCard);
    }

    private void manageEvents(Card card) {
        System.out.println("What do you want to do?\n 1)add task    2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add task":
                System.out.println("Give the name to your task");
                String taskName = scanner.nextLine();
                System.out.println("Give the description to your task");
                String taskDescription = scanner.nextLine();
                tasksService.addNewTask(taskName, taskDescription, card);
            case "2":
            case "open":
                showTaskDescription();
                break;
            case "3":
            case "exit":
                // TODO: return to cardsScreen();
                System.out.println("exit");
            default:
                System.out.println("Please, enter correct value");
                manageEvents(card);
                break;
        }
    }

    private void showTaskDescription() {
        System.out.println("Choose the task name: ");
        for (int i = 0; i < userTasks.size(); i++) {
            System.out.println(i + 1 + ")" + userTasks.get(i).getName());
        }
        Task openingTask = userTasks.get(Integer.valueOf(scanner.nextLine()) - 1);
        for (Task task : userTasks) {
            if (openingTask.getName().equals(task.getName())) {
                String description = tasksService.findTaskByName(openingTask.getName()).getDescription();
                System.out.println(task.getName() + ":" + " " + description);
            } else {
                System.out.println("Please, enter correct value");
                showTaskDescription();
            }
        }
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
