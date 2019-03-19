package dao;

import models.Card;
import models.Task;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TasksDao implements SimpleDao{

    private JdbcTemplate jdbcTemplate;
    private Connection connection = null;

    private final String SQL_SELECT_TASKS_OF_USER = "select task.id, task.name, task.description\n" +
            "from task_to_user inner join task on task_to_user.user_id = task.user_id where task_to_user.user_id = ?";
    private final String SQL_SELECT_TASK_BY_NAME = "select * from task where name=?";


    public TasksDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            jdbcTemplate = context.getBean(JdbcTemplate.class);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> findAllUserTasks(String login) {
        UsersDao usersDao = new UsersDao();
        User user = usersDao.findByLogin(login).get(0);
        return jdbcTemplate.query(SQL_SELECT_TASKS_OF_USER, tasksRowMapper, user.getId());
    }

    public List<Task> findAllTasksFromCard(String cardName) {
        CardsDao cardsDao = new CardsDao();
        Card card = cardsDao.find(cardName).get(0);
        return jdbcTemplate.query(SQL_SELECT_TASKS_OF_USER,tasksRowMapper,card.getId());
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
    public void add(String name, String login) {

    }
}
