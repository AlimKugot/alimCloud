package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <T> {
    Optional<T> find(long id);
    void save(T model);
    void update(T model);
    void delete(long id);

    List<T> findAll();
}