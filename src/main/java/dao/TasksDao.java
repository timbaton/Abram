package dao;

import models.Card;
import models.Task;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class TasksDao implements BaseDao {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_TASKS_OF_USER = "select task.id, task.name, task.description\n" +
            "from task_to_user inner join task on task_to_user.user_id = task.user_id where task_to_user.user_id = ?";
    private final String SQL_SELECT_TASK_BY_NAME = "select * from task where name=?";
    private final String SQL_SELECT_TASKS_FROM_CARD = "select task.id, task.name, task.description, task.card_id\n" +
            "from task inner join card on task.card_id = card.id where card.name = ?";
    private final String SQL_INSERT_TASK_INTO_CARD = "insert into task(description,card_id, name) values (?,?,?)";

    @Autowired
    public TasksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findAllUserTasks(User user) {
        return jdbcTemplate.query(SQL_SELECT_TASKS_OF_USER, tasksRowMapper, user.getId());
    }

    public List<Task> findAllTasksFromCard(String cardName) {
        return jdbcTemplate.query(SQL_SELECT_TASKS_FROM_CARD, tasksRowMapper, cardName);
    }

    private RowMapper<Task> tasksRowMapper = (resultSet, i) -> Task.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .description(resultSet.getString("description"))
            .build();

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> find(String name) {
        return jdbcTemplate.query(SQL_SELECT_TASK_BY_NAME, tasksRowMapper, name);
    }

    @Override
    public void add(Object object) {

    }

    public void addTask(String taskName, String taskDescription, Card card) {
        jdbcTemplate.update(SQL_INSERT_TASK_INTO_CARD, taskDescription, card.getId(), taskName);
    }
}
