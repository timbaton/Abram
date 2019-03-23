package screens;

import base.BaseAbstractClass;
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
public class TasksScreen extends BaseAbstractClass {
    private Scanner scanner;
    private List<Task> userTasks;

    private final TasksDao tasksDao;
    private final TasksService tasksService;

    private Card curCard;


    @Autowired
    public TasksScreen(ScannerFactory scannerFactory, TasksDao tasksDao, TasksService tasksService) {
        this.scanner = scannerFactory.getSystemIn();
        this.tasksDao = tasksDao;
        this.tasksService = tasksService;
    }

    public void openScreen() {
        userTasks = tasksDao.findAllTasksFromCard(curCard.getName());
        showTasks();
        manageEvents();
    }

    public void manageEvents() {
        System.out.println("What do you want to do?\n 1)add task    2)open      3)exit");
        switch (scanner.nextLine()) {
            case "1":
            case "add task":
                System.out.println("Give the name to your task");
                String taskName = scanner.nextLine();
                System.out.println("Give the description to your task");
                String taskDescription = scanner.nextLine();
                tasksService.addNewTask(taskName, taskDescription, curCard);
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
                manageEvents();
                break;
        }
    }

    private void showTaskDescription() {
        System.out.println("Choose the task name: ");
        for (int i = 0; i < userTasks.size(); i++) {
            System.out.println(i + 1 + ")" + userTasks.get(i).getName());
        }
        int index = Integer.valueOf(scanner.nextLine()) - 1;
        if (index <= userTasks.size()) {
            Task openingTask = userTasks.get(index);
            for (Task task : userTasks) {
                if (openingTask.getName().equals(task.getName())) {
                    String description = tasksService.findTaskByName(openingTask.getName()).getDescription();
                    System.out.println(task.getName() + ":" + " " + description);
                } else {
                    System.out.println("Please, enter correct value");
                    showTaskDescription();
                }
            }
        } else
            System.out.println("Please, enter correct value");
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

    @Override
    public void quit() {

    }

    public void setCard(Card card) {
        curCard = card;
    }
}
