package dao;

import models.Desk;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DesksDao implements SimpleDao {

    private JdbcTemplate jdbcTemplate;
    private Connection connection = null;

    private final String SQL_SELECT_DESK_BY_NAME = "SELECT * FROM desk WHERE name= ?";
    private final String SQL_SELECT_DESKS_OF_USER = "select desk.id, desk.name, desk.date_of_creation, desk.creator\n" +
            "from user_to_desk inner join desk on user_to_desk.user_id = desk.creator\n" +
            "where user_to_desk.user_id = ?";

    public DesksDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            jdbcTemplate = context.getBean(JdbcTemplate.class);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Desk> findAllUserDesks(String login) {
        UsersDao usersDao = new UsersDao();
        User user = usersDao.findByLogin(login).get(0);
        return jdbcTemplate.query(SQL_SELECT_DESKS_OF_USER, deskRowMapper, user.getId());
    }

    private RowMapper<Desk> deskRowMapper = (resultSet, i) -> Desk.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .dataOfCreation(resultSet.getDate("date_of_creation"))
            .creator(resultSet.getLong("creator"))
            .build();

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<Desk> find(String name) {
        return jdbcTemplate.query(SQL_SELECT_DESK_BY_NAME, deskRowMapper, name);
    }
}
