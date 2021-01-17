package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersDaoJdbcTemplateImpl extends UsersDao {
    private final JdbcTemplate template;

    @Autowired
    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> rowMapper = (resultSet, i) -> new User.Builder()
        .setId(resultSet.getInt("id"))
        .setUserName(resultSet.getString("username"))
        .setEmail(resultSet.getString("email"))
        .setPassword(resultSet.getString("password"))
        .build();

    @Override
    public Optional<User> find(String email) {
        List<User> userList = template.query(SQL_SELECT_BY_EMAIL, rowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userList.get(0));
    }

    @Override
    public void save(User model) {
        if (find(model.getEmail()).isEmpty()) {
            template.update(SQL_INSERT_INTO_USERS, model.getUserName(),
                    model.getEmail() , model.getPassword());
        }
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(String email) {
        if (find(email).isPresent()) {
            template.update(SQL_DELETE_BY_EMAIL, email);
        }
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL, rowMapper);
    }
}
