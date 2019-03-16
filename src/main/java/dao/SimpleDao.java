package dao;

import java.util.List;

public interface SimpleDao<T> {
    List<T> findAll();
    T findByName(String name);
    T findByLogin(String login);
}
