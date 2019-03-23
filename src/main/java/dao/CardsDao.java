package dao;

import models.Card;
import models.Desk;
import models.User;
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
import java.util.List;

@Component
@Repository
public class CardsDao implements BaseDao {

    @Autowired
    private DesksDao desksDao;
    @Autowired
    private Connection connection;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_CARDS_FROM_DESK = "select card.id, card.name, card.date_of_creation from card " +
            "inner join desk on card.desk_id = desk.id where desk.name = ?";
    private final String SQL_SELECT_CARD_BY_NAME = "select * from card where name = ?";
    private final String SQL_INSERT_CARD_INTO_DESK = "insert into card(date_of_creation, name, desk_id) VALUES(?,?,?)";

    @Autowired
    public CardsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    public void add(Object object) {

    }


    public void addCard(String name, Desk desk) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_CARD_INTO_DESK);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, name);
            ps.setInt(3, desk.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
