package dao;

import models.Card;
import models.Desk;
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

public class CardsDao<T> implements SimpleDao {

    private JdbcTemplate jdbcTemplate;
    private Connection connection = null;

    private final String SQL_SELECT_CARDS_FROM_DESK = "select card.id, card.name, card.date_of_creation\n" +
            "from card inner join desk on card.desk_id = desk.id where desk.id = ?";

    public CardsDao() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
            jdbcTemplate = context.getBean(JdbcTemplate.class);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Card> findAllCardsFromDesk(String deskName) {
        DesksDao desksDao = new DesksDao();
        Desk desk = desksDao.find(deskName).get(0);
        return jdbcTemplate.query(SQL_SELECT_CARDS_FROM_DESK, cardsRowMapper, desk.getId());
    }

    private RowMapper<Card> cardsRowMapper = (resultSet, i) -> Card.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .dateOfCreation(resultSet.getDate("date_of_creation"))
            .build();

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object find(String name) {
        return null;
    }
}
