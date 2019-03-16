package dao;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsersDao {
    private Connection connection = null;
    private List<User> users;

    private final String SQL_SELECT_ALL = "SELECT * FROM \"user\"";
    private final String SQL_SELECT_USER_BY_NAME = "SELECT * FROM \"user\" WHERE name= ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsersDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.users = new ArrayList<>();
    }

    public List<User> findAll() {
        // подключение к бд через бины. объявленные в spring-config.xml
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    public User find(String name) {
        return (User) jdbcTemplate.query(SQL_SELECT_USER_BY_NAME, rowMapper, name);
    }

    private RowMapper<User> rowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .build();
}
