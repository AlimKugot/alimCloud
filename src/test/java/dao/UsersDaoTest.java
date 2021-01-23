package dao;

import database.InitDatabase;
import model.User;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDaoTest {
    private final dao.UsersDao usersDao;
    private static final String IVAN_EMAIL = "ivanNullpointerov@gmail.com";
    private static final String JOHN_EMAIL = "johnNullpointerov@gmail.com";
    private final User ivan;
    private final User john;

    private UsersDaoTest() {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
        usersDao.delete(IVAN_EMAIL);
        usersDao.delete(JOHN_EMAIL);
        ivan = new User.Builder()
                .userName("Ivan")
                .email(IVAN_EMAIL)
                .password("881293434ivan")
                .build();
        john = new User.Builder()
                .userName("john")
                .email(JOHN_EMAIL)
                .password("58274591john")
                .build();
        usersDao.save(ivan);
        usersDao.save(john);
    }

    @Test
    public void save() {
        usersDao.save(ivan);
        assertFalse(usersDao.find(IVAN_EMAIL).isEmpty());
    }

    @Test
    public void delete() {
        usersDao.delete(IVAN_EMAIL);
        assertTrue(usersDao.find(IVAN_EMAIL).isEmpty());
    }

    @Test
    public void update() {

    }

    @Test
    public void findAll() {
        usersDao.save(ivan);
        usersDao.save(john);
        List<User> userList = usersDao.findAll();
        int countOfMatches = 0;
        for (User user : userList) {
            String userMail = user.getEmail();
            if (userMail.equals(JOHN_EMAIL) || userMail.equals(IVAN_EMAIL))
                countOfMatches++;
        }
        assertEquals(countOfMatches, 2);
    }
}
