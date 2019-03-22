package services;

import dao.TasksDao;
import models.Card;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import screens.TasksScreen;
import utils.ScannerFactory;

import java.util.Scanner;

public class TasksService {
    private Scanner scanner;

    @Autowired
    private TasksScreen tasksScreen;
    @Autowired
    private TasksDao tasksDao;

    @Autowired
    public TasksService(ScannerFactory scannerFactory) {
        this.scanner = scannerFactory.getSystemIn();
    }

    public void addNewTask(String taskName, String taskDescription, Card card) {
        tasksDao.addTask(taskName, taskDescription, card);
        System.out.println("Added new task" + " " + taskName);
        switch (scanner.nextLine()) {
            case "1":
            case "exit":
                System.out.println("Returned to tasks");
                tasksScreen.openScreen(card);
                break;
            default:
                System.out.println("Please, enter correct value");
                break;
        }
    }

    public Task findTaskByName(String name) {
        Task task = tasksDao.find(name).get(0);
        return task;
    }
}
