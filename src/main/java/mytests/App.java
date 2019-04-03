package mytests;

import models.Card;
import models.Desk;
import models.Task;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    //    void print() {
//        System.out.println("hello");
//    }
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/abram");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "postgres");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("User.hbm.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Desk.class);
        configuration.addAnnotatedClass(Card.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.setProperty("hibernate.show_sql", "true");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
    }
}
