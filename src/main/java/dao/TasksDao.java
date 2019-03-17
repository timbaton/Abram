package dao;

import models.Task;
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
    
    private final String SQL_SELECT_TASKS_OF_USER = "select * from task_to_user\n" +
            "       inner join task on task_to_user.user_id = task.user_id where task_to_user.user_id = ?\n";

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
        return jdbcTemplate.query(SQL_SELECT_TASKS_OF_USER, tasksRowMapper, login);
    }

    private RowMapper<Task> tasksRowMapper = (resultSet, i) -> Task.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .description(resultSet.getString("description"))
            .card(resultSet.getLong("card_id"))
            .user(resultSet.getLong("user_id"))
            .build();
}
