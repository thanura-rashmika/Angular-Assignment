package lk.ijse.absd.servlets.repository.other;

import java.sql.SQLException;
import java.util.List;

public interface SuperRepo<T,K> {
    boolean add(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(K k) throws SQLException;
    T search(K k) throws SQLException;
    List<T> getAll () throws SQLException;
}
