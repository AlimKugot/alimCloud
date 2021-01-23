package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersDaoJdbcImpl extends UsersDao {
    private final Connection connection;

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
                long id = resultSet.getLong("id");

                User user = new User.Builder()
                        .id(id)
                        .userName(userName)
                        .email(email)
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
            if (find(model.getEmail()).isEmpty()) {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_USERS);
                ps.setString(1, model.getUserName());
                ps.setString(2, model.getEmail());
                ps.setString(3, model.getPassword());
                ps.executeUpdate();
            }
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
            if (find(email).isPresent()) {
                PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_EMAIL);
                ps.setString(1, email);
                ps.executeUpdate();
            }
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }



    @Override
    public List<User> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String userName = resultSet.getString("username");
                String email = resultSet.getString("email");

                User user = new User.Builder()
                        .id(id)
                        .userName(userName)
                        .email(email)
                        .build();
                userList.add(user);
            }
            return userList;
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }
}