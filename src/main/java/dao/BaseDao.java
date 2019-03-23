package dao;

import models.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Component
public interface BaseDao<T> {
    List<T> findAll();
    T find(String name);
    void add(Object object);
}
