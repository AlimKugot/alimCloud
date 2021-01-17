package dao;

import repository.UserSQLQueries;
import model.User;
import org.springframework.stereotype.Component;

@Component
public abstract class UsersDao implements CrudDao<User> {
    protected static final String SQL_SELECT_ALL;
    protected static final String SQL_SELECT_BY_EMAIL;
    protected static final String SQL_INSERT_INTO_USERS;
    protected static final String SQL_DELETE_BY_EMAIL;
    protected static final String SQL_UPDATE_EMAIL;

    static {
        SQL_SELECT_ALL = UserSQLQueries.getSQL_SELECT_ALL();
        SQL_SELECT_BY_EMAIL = UserSQLQueries.getSQL_SELECT_BY_EMAIL();
        SQL_INSERT_INTO_USERS = UserSQLQueries.getSQL_INSERT_INTO_USERS();
        SQL_DELETE_BY_EMAIL = UserSQLQueries.getSQL_DELETE_BY_EMAIL();
        SQL_UPDATE_EMAIL = UserSQLQueries.getSQL_UPDATE_EMAIL();
    }
}