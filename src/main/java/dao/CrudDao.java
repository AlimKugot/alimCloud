package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <T> {
    Optional<T> find(String email);
    void save(T model);
    void update(T model);
    void delete(String email);

    List<T> findAll();
}
