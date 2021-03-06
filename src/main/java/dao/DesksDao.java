package dao;

import models.Desk;
import models.User;
import mytests.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class DesksDao implements BaseDao {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_DESK_BY_NAME = "SELECT * FROM desk WHERE name= ?";
    private final String SQL_SELECT_DESKS_OF_USER = "select desk.id, desk.name, desk.date_of_creation, desk.creator from desk inner join\n" +
            "(select ud.desk_id from user_to_desk ud inner join \"user\" u on ud.user_id = u.id where u.id = ?) as t on desk.id = t.desk_id";
    private final String SQL_INSERT_NEW_DESK = "with ins1 as (insert into desk (name, date_of_creation, creator) VALUES (?, ?, ?)\n" +
            "returning id, creator)\n" +
            "insert into user_to_desk(desk_id, user_id) values ((select id from ins1), (select creator from ins1))";
    private final String SQL_INSERT_DESK_USER = "insert into user_to_desk(user_id, desk_id) values (?,?)";
    private final String SQL_DELETE_DESK = "delete from desk where desk.name = ?";


    @Autowired
    public DesksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Desk> findAllUserDesks(User user) {
        return jdbcTemplate.query(SQL_SELECT_DESKS_OF_USER, deskRowMapper, user.getId());
    }

    private RowMapper<Desk> deskRowMapper = (resultSet, i) -> Desk.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .dataOfCreation(resultSet.getString("date_of_creation"))
            .creator(resultSet.getInt("creator"))
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
    public void add(Object object) {

    }

    public void add(String name, User user) {
        jdbcTemplate.update(SQL_INSERT_NEW_DESK, name, Timestamp.valueOf(LocalDateTime.now()),user.getId());
    }

    public void delete(String deskName){
        jdbcTemplate.update(SQL_DELETE_DESK, deskName);
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
