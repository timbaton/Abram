package dao;

import models.Card;
import models.Desk;
import models.Task;
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
import java.util.List;

public class CardsDao implements SimpleDao {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_CARDS_FROM_DESK = "select card.id, card.name, card.date_of_creation from card " +
            "inner join desk on card.desk_id = desk.id where desk.name = ?";
    private final String SQL_SELECT_CARD_BY_NAME = "select * from card where name = ?";

    @Autowired
    public CardsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* public CardsDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public List<Card> findAllCardsFromDesk(String deskName) {
        return jdbcTemplate.query(SQL_SELECT_CARDS_FROM_DESK, cardsRowMapper, deskName);
    }

    private RowMapper<Card> cardsRowMapper = (resultSet, i) -> Card.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .dateOfCreation(resultSet.getString("date_of_creation"))
            .build();

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<Card> find(String name) {
        return jdbcTemplate.query(SQL_SELECT_CARD_BY_NAME, cardsRowMapper, name);
    }

    @Override
    public void add(String name, User user) {

    }
}
