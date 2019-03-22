package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import screens.TasksScreen;

@Component
public class TasksService {
    private final TasksScreen tasksScreen;

    @Autowired
    public TasksService(TasksScreen tasksScreen) {
        this.tasksScreen = tasksScreen;
    }
}
