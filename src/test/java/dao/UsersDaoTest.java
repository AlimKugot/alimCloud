package dao;

import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDaoTest {
    private static final dao.UsersDao usersDao;

    static {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
    }

    @Test
    @Order(1)
    public void saveFindDelete() {
        final String IVAN_EMAIL = "ivanNullpointerov@gmail.com";
        User ivan = new User.Builder()
                .setUserName("Ivan")
                .setEmail(IVAN_EMAIL)
                .setPassword("881293434ivan")
                .build();
        usersDao.delete(IVAN_EMAIL);
        assertTrue(usersDao.find(IVAN_EMAIL).isEmpty());
        usersDao.save(ivan);
        assertFalse(usersDao.find(IVAN_EMAIL).isEmpty());
        usersDao.delete(IVAN_EMAIL);
    }

    @Test
    public void update() {

    }

    @Test
    @Order(2)
    public void findAll() {
        final String IVAN_EMAIL = "ivanNullpointerov@gmail.com";
        final String JOHN_EMAIL = "johnNullpointerov@gmail.com";
        usersDao.delete(IVAN_EMAIL);
        usersDao.delete(JOHN_EMAIL);
        User ivan = new User.Builder()
                .setUserName("Ivan")
                .setEmail(IVAN_EMAIL)
                .setPassword("881293434ivan")
                .build();
        User john = new User.Builder()
                .setUserName("john")
                .setEmail(JOHN_EMAIL)
                .setPassword("58274591john")
                .build();
        usersDao.save(ivan);
        usersDao.save(john);
        List<User> userList = usersDao.findAll();
        int countOfMatches = 0;
        for (User user : userList) {
            if (user.getEmail().equals(JOHN_EMAIL) || user.getEmail().equals(IVAN_EMAIL))
                countOfMatches++;
        }
        assertEquals(countOfMatches, 2);
        usersDao.delete(IVAN_EMAIL);
        usersDao.delete(JOHN_EMAIL);
    }
}
