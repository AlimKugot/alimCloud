package dao;

import database.SQLQueries;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UsersDao {
    private final Connection connection;
    private static final String SQL_SELECT_ALL;
    private static final String SQL_SELECT_BY_EMAIL;
    private static final String SQL_INSERT_INTO_USERS;
    private static final String SQL_DELETE_BY_EMAIL;
    private static final String SQL_UPDATE_EMAIL;

    static {
        SQLQueries queries = new SQLQueries();
        SQL_SELECT_ALL = queries.getSQL_SELECT_ALL();
        SQL_SELECT_BY_EMAIL = queries.getSQL_SELECT_BY_EMAIL();
        SQL_INSERT_INTO_USERS = queries.getSQL_INSERT_INTO_USERS();
        SQL_DELETE_BY_EMAIL = queries.getSQL_DELETE_BY_EMAIL();
        SQL_UPDATE_EMAIL = queries.getSQL_UPDATE_EMAIL();
    }

    public UsersDaoJdbcImpl(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<User> find(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String userName = resultSet.getString("username");
                Integer id = resultSet.getInt("id");

                User user = new User.Builder()
                        .setId(id)
                        .setUserName(userName)
                        .setEmail(email)
                        .build();
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_USERS);
            ps.setString(1, model.getUserName());
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getPassword());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }

    @Override
    public void update(User model) {
        //todo : I don't know, what I need to change
    }

    private void updateEmail(String oldEmail, String newEmail) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_EMAIL);
            ps.setString(1, oldEmail);
            ps.setString(2, newEmail);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }


    @Override
    public void delete(String email) {
        try  {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_EMAIL);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = ps.executeQuery()) {
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String email = resultSet.getString("email");

                User user = new User.Builder()
                        .setId(id)
                        .setUserName(userName)
                        .setEmail(email)
                        .build();
                userList.add(user);
            }
            return userList;
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }
}
