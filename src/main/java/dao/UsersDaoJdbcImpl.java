package dao;

import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import security.Crypto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersDaoJdbcImpl extends CrudDaoJdbc<User> implements UsersDao {
    private final Connection connection;
    private final static Logger logger = LogManager.getLogger(UsersDaoJdbcImpl.class);

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    public boolean isExists(String email) {
        boolean flag;
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            flag = resultSet.next();
            resultSet.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
            flag = false;
        }
        return flag;
    }

    @Override
    public Optional<User> find(long id) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setLong(1, id);
            ps.executeQuery();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> find(String email, String password) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String realHashedPassword = resultSet.getString("password");
                if (Crypto.matchesPasswords(password, realHashedPassword)) {
                    String userName = resultSet.getString("username");
                    long id = resultSet.getLong("id");
                    String roleString = resultSet.getString("role");
                    Role role;

                    if (roleString.equals(Role.user.toString())) {
                        role = Role.user;
                    } else if (roleString.equals(Role.admin.toString())) {
                        role = Role.admin;
                    } else {
                        role = Role.unknown;
                    }

                    User user = new User.Builder()
                            .id(id)
                            .userName(userName)
                            .email(email)
                            .role(role)
                            .build();
                    return Optional.of(user);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) {
        if (!isExists(model.getEmail())) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_USERS)) {
                ps.setString(1, model.getUserName());
                ps.setString(2, model.getEmail());
                ps.setString(3, model.getPassword());
                ps.setString(4, model.getRole().toString());
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }

    @Override
    public void update(User model) {
        //todo : I don't know, what I need to change
    }

    private void updateEmail(String oldEmail, String newEmail) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_EMAIL)) {
            ps.setString(1, oldEmail);
            ps.setString(2, newEmail);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
    }

    @Override
    public void delete(long id) {
        if (find(id).isPresent()) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }

    public void delete(String email) {
        if (isExists(email)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_EMAIL)) {
                ps.setString(1, email);
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }


    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

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
            logger.error(sqlException.getMessage(), sqlException);
        }
        return userList;
    }
}