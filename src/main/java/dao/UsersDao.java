package dao;

import database.sqlQueries.UsersSql;
import model.User;

import java.util.Optional;

public interface UsersDao extends CrudDao<User> {
    String SQL_SELECT_ALL = UsersSql.getSQL_SELECT_ALL();
    String SQL_SELECT_BY_EMAIL = UsersSql.getSQL_SELECT_BY_EMAIL();
    String SQL_SELECT_BY_ID = UsersSql.getSQL_SELECT_BY_ID();
    String SQL_INSERT_INTO_USERS = UsersSql.getSQL_INSERT_INTO_USERS();
    String SQL_DELETE_BY_EMAIL = UsersSql.getSQL_DELETE_BY_EMAIL();
    String SQL_DELETE_BY_ID = UsersSql.getSQL_DELETE_BY_ID();
    String SQL_UPDATE_EMAIL = UsersSql.getSQL_UPDATE_EMAIL();

    Optional<User> find(String email, String password);
    boolean isExists(String email);
    void delete(String email);
}