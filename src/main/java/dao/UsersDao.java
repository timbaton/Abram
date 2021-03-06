package dao;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class UsersDao implements BaseDao {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_ALL = "SELECT * FROM \"user\"";
    private final String SQL_SELECT_USER_BY_NAME = "SELECT * FROM \"user\" WHERE name= ?";
    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE login= ?";
    private final String SQL_INSERT_USER = "insert into \"user\"(login, password) values(?,?)";

    @Autowired
    public UsersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        // подключение к бд через бины. объявленные в spring-config.xml
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> find(String name) {
        return jdbcTemplate.query(SQL_SELECT_USER_BY_NAME, userRowMapper, name);
    }

    @Override
    public void add(Object object) {
        User user = (User) object;
        jdbcTemplate.update(SQL_INSERT_USER, user.getLogin(), user.getPassword());
    }

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
