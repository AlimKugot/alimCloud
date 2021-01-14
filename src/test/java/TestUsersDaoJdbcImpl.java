
import dao.UsersDaoJdbcImpl;
import helperStatic.SignUpAndIn;
import model.User;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUsersDaoJdbcImpl {
    private static final UsersDaoJdbcImpl usersDao;

    static {
        Connection connection = SignUpAndIn.initToDatabase();
        usersDao = new UsersDaoJdbcImpl(connection);
    }

    @Test
    @Order(1)
    public void saveAndFindByEmail() {
        final String IVAN_MAIL = "ivanNullpointerov@gmail.com";
        User ivan = new User.Builder()
                .setUserName("Ivan")
                .setEmail(IVAN_MAIL)
                .setPassword("881293434ivan")
                .build();
        assertFalse(usersDao.find(IVAN_MAIL).isEmpty());
        usersDao.save(ivan);
        assertTrue(usersDao.find(IVAN_MAIL).isEmpty());
        usersDao.delete(IVAN_MAIL);
    }

    @Test
    @Order(2)
    public void delete() {
        final String JOHN_MAIL = "johnNullpointerov@gmail.com";
        User john = new User.Builder()
                .setUserName("john")
                .setEmail(JOHN_MAIL)
                .setPassword("58274591john")
                .build();
        usersDao.save(john);
        assertFalse(usersDao.find(JOHN_MAIL).isEmpty());
        usersDao.delete(JOHN_MAIL);
        assertTrue(usersDao.find(JOHN_MAIL).isEmpty());
    }

    @Test
    public void update() {
        //todo : write test
    }

    @Test
    @Order(3)
    public void findAll() {
        final String IVAN_MAIL = "ivanNullpointerov@gmail.com";
        final String JOHN_MAIL = "johnNullpointerov@gmail.com";
        User ivan = new User.Builder()
                .setUserName("Ivan")
                .setEmail(IVAN_MAIL)
                .setPassword("881293434ivan")
                .build();
        User john = new User.Builder()
                .setUserName("john")
                .setEmail(JOHN_MAIL)
                .setPassword("58274591john")
                .build();
        usersDao.save(ivan);
        usersDao.save(john);
        List<User> userList = usersDao.findAll();
        for (User user : userList) {
            assertTrue(user.equals(john) || user.equals(ivan));
        }
    }
}
