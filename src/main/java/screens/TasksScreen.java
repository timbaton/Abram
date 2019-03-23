package screens;

import base.BaseAbstractClass;
import base.BaseScreen;
import dao.TasksDao;
import models.Card;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TasksService;
import utils.PrintManager;
import utils.ScannerFactory;

import java.util.List;
import java.util.Scanner;

@Component
public class TasksScreen extends BaseAbstractClass {
    private Scanner scanner;
    private List<Task> userTasks;

    private final TasksDao tasksDao;
    private final TasksService tasksService;
    private PrintManager printManager;
    private Card curCard;


    @Autowired
    public TasksScreen(ScannerFactory scannerFactory, TasksDao tasksDao, TasksService tasksService, PrintManager printManager) {
        this.scanner = scannerFactory.getSystemIn();
        this.tasksDao = tasksDao;
        this.printManager = printManager;
        this.tasksService = tasksService;
    }

    public void openScreen() {
        userTasks = tasksDao.findAllTasksFromCard(curCard.getName());
        showTasks();
        manageEvents();
    }

    public void manageEvents() {
        printManager.print("What do you want to do?\n 1)add task    2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add task":
                addNewTask();
            case "2":
            case "open":
                showTaskDescription();
                break;
            case "3":
            case "exit":
                quit();
            default:
                printManager.printInNewScreen("Please, enter correct value");
                manageEvents();
                break;
        }
    }

    private void addNewTask() {
        printManager.printInNewScreen("Give the name to your task");
        String taskName = scanner.nextLine();

        printManager.printInNewScreen("Give the description to your task");
        String taskDescription = scanner.nextLine();

        tasksService.addNewTask(taskName, taskDescription, curCard);
    }

    private void showTaskDescription() {
        printManager.printInNewScreen("Choose the task name: ");

        StringBuilder tasksList = new StringBuilder();
        for (int i = 0; i < userTasks.size(); i++) {
            tasksList.append(i + 1).append(")").append(userTasks.get(i).getName());
            if (i != userTasks.size() - 1) {
                tasksList.append("\n");
            }
        }
        printManager.print(tasksList.toString());

        int index = Integer.valueOf(scanner.nextLine()) - 1;
        if (index < userTasks.size()) {
            Task openingTask = userTasks.get(index);
            for (Task task : userTasks) {
                String description = tasksService.findTaskByName(openingTask.getName()).getDescription();
                printManager.print(task.getName() + ":" + " " + description);
            }
        } else {
            printManager.print("Please, enter correct value");
            showTaskDescription();
        }
    }

    private void showTasks() {
        StringBuilder tasksList = new StringBuilder();
        if (userTasks.size() != 0) {
            printManager.print("This card has tasks: ");
            for (int i = 0; i < userTasks.size(); i++) {
                tasksList.append(i + 1).append(")").append(userTasks.get(i).getName());
                if (i != userTasks.size() - 1) {
                    tasksList.append("\n");
                }
            }
        } else tasksList.append("No tasks in the card!");

        printManager.print(tasksList.toString());
    }

    void setCard(Card card) {
        curCard = card;
    }
}
