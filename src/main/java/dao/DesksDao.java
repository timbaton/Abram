package dao;

import models.Desk;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DesksDao implements SimpleDao {

    private final JdbcTemplate jdbcTemplate;
    private final Connection connection;

    private final String SQL_SELECT_DESK_BY_NAME = "SELECT * FROM desk WHERE name= ?";
    private final String SQL_SELECT_DESKS_OF_USER = "select desk.id, desk.name, desk.date_of_creation, desk.creator\n" +
            "from user_to_desk inner join desk on user_to_desk.user_id = desk.creator\n" +
            "where user_to_desk.user_id = ?";
    private final String SQL_INSERT_NEW_DESK = "insert into desk(name,date_of_creation,creator) values(?,?,?)";
    private final String SQL_INSERT_DESK_USER = "insert into user_to_desk(user_id, desk_id) values (?,?)";

    public DesksDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        this.jdbcTemplate = context.getBean(JdbcTemplate.class);
        this.connection = context.getBean(Connection.class);
    }


    public List<Desk> findAllUserDesks(User user) {
        return jdbcTemplate.query(SQL_SELECT_DESKS_OF_USER, deskRowMapper, user.getId());
    }

    private RowMapper<Desk> deskRowMapper = (resultSet, i) -> Desk.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .dataOfCreation(resultSet.getString("date_of_creation"))
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

    @Override
    public void add(String name, User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_NEW_DESK);
            ps.setString(1, name);
            Date date = new Date();
            ps.setString(2, String.valueOf(date));
            ps.setInt(3, Math.toIntExact(user.getId()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(printDesks(findAllUserDesks(user)));
    }

    private StringBuilder printDesks(List<Desk> desks) {
        StringBuilder result = new StringBuilder();
        System.out.println("Your desks: ");
        for (Desk desk : desks) {
            result.append(desk.getName()).append("\n");
        }
        return result;
    }
}
