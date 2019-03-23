package services;

import dao.TasksDao;
import models.Card;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksService {
    @Autowired
    private TasksDao tasksDao;

    public void addNewTask(String taskName, String taskDescription, Card card) {
        tasksDao.addTask(taskName, taskDescription, card);
    }

    public Task findTaskByName(String name) {
        return tasksDao.find(name).get(0);
    }
}
