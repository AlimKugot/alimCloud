package database;

import security.Crypto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginConfirm {
    private static Connection connection;
    private static final String SQL_SELECT_ALL;
    private static final String SQL_SELECT_BY_EMAIL;
    private static final String SQL_INSERT_INTO_USERS;
    private static final String SQL_DELETE_BY_EMAIL;
    private static final String SQL_UPDATE_EMAIL;

    static {
        connection = InitDatabase.getConnection();
        SQLQueries queries = new SQLQueries();
        SQL_SELECT_ALL = queries.getSQL_SELECT_ALL();
        SQL_SELECT_BY_EMAIL = queries.getSQL_SELECT_BY_EMAIL();
        SQL_INSERT_INTO_USERS = queries.getSQL_INSERT_INTO_USERS();
        SQL_DELETE_BY_EMAIL = queries.getSQL_DELETE_BY_EMAIL();
        SQL_UPDATE_EMAIL = queries.getSQL_UPDATE_EMAIL();
    }


    public static boolean confirmEmailAndPassword(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return Crypto.matchesPasswords(password, hashedPassword);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return false;
    }


}
