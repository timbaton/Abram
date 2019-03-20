package dao;

import models.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface SimpleDao<T> {
    List<T> findAll();
    T find(String name);
    void add(String name, User user);
}
