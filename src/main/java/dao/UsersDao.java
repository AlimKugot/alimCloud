package dao;

import database.SQLQueries;
import model.Role;
import model.User;

import java.util.Optional;

public abstract class UsersDao implements CrudDao<User> {
    protected static final String SQL_SELECT_ALL;
    protected static final String SQL_SELECT_BY_EMAIL;
    protected static final String SQL_INSERT_INTO_USERS;
    protected static final String SQL_DELETE_BY_EMAIL;
    protected static final String SQL_UPDATE_EMAIL;

    static {
        SQLQueries queries = new SQLQueries();
        SQL_SELECT_ALL = queries.getSQL_SELECT_ALL();
        SQL_SELECT_BY_EMAIL = queries.getSQL_SELECT_BY_EMAIL();
        SQL_INSERT_INTO_USERS = queries.getSQL_INSERT_INTO_USERS();
        SQL_DELETE_BY_EMAIL = queries.getSQL_DELETE_BY_EMAIL();
        SQL_UPDATE_EMAIL = queries.getSQL_UPDATE_EMAIL();
    }

    abstract public Optional<User> find(String email, String password);
}