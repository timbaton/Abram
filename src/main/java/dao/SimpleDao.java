package dao;

import java.util.List;

public interface SimpleDao<T> {
    List<T> findAll();
    T find(String name);
}
