package dao;

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

public class TasksDao {

    private JdbcTemplate jdbcTemplate;
    private Connection connection = null;

    private final String SQL_SELECT_TASKS_OF_USER = "select task.id, task.name, task.description\n" +
            "from task_to_user inner join task on task_to_user.user_id = task.user_id where task_to_user.user_id = ?";

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

    private RowMapper<Task> tasksRowMapper = (resultSet, i) -> Task.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .description(resultSet.getString("description"))
            .build();
}
