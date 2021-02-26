package dao;

import java.util.List;

public interface CrudDao <T> {
    boolean find(String email);
    void save(T model);
    void update(T model);
    void delete(String email);

    List<T> findAll();
}
