package lk.ijse.absd.servlets.service.other;

import java.sql.SQLException;
import java.util.List;

public interface SuperService<T,K> {
    boolean add(T t);
    boolean update(T t);
    boolean delete(K k);
    T search(K k);
    List<T>getAll();
}
