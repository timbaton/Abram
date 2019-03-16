package dao;

import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDao implements SimpleDao {
    private Connection connection = null;
    private List<User> users;

    private final String SQL_SELECT_ALL = "SELECT * FROM \"user\"";
    private final String SQL_SELECT_USER_BY_NAME = "SELECT * FROM \"user\" WHERE name= ?";
    private final String SQL_SELECT_DESKS_OF_USER = "select desk.id, desk.name from user_to_desk\n" +
            "inner join desk on user_to_desk.user_id = desk.creator where user_to_desk.user_id = ?\n" +
            "group by desk.name, desk.id";
    private final String SQL_SELECT_TASKS_OF_USER = "select task.id, task.name from task_to_user\n" +
            "       inner join task on task_to_user.user_id = task.user_id where task_to_user.user_id = ?\n" +
            "group by task.name, task.id";
    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE login= ?";

    private JdbcTemplate jdbcTemplate;

    public UsersDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            jdbcTemplate = context.getBean(JdbcTemplate.class);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.users = new ArrayList<>();
    }

    public List<User> findAll() {
        // подключение к бд через бины. объявленные в spring-config.xml
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    public List<User> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_USER_BY_NAME, userRowMapper, name);
    }

    @Override
    public List<User> findByLogin(String login) {
        return jdbcTemplate.query(SQL_SELECT_USER_BY_LOGIN, userRowMapper, login);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .build();
}
