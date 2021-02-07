package dao;

import fake.FakeUsers;
import model.User;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDaoTest {
    FakeUsers fakeUsers;

    private UsersDaoTest() {
        fakeUsers = new FakeUsers();
    }

    @Test
    public void save() {
        fakeUsers.usersDao.save(fakeUsers.ivan);
        assertFalse(fakeUsers.usersDao.find(fakeUsers.IVAN_EMAIL).isEmpty());
    }

    @Test
    public void delete() {
        fakeUsers.usersDao.delete(fakeUsers.IVAN_EMAIL);
        assertTrue(fakeUsers.usersDao.find(fakeUsers.IVAN_EMAIL).isEmpty());
    }

    @Test
    public void update() {

    }

    @Test
    public void findAll() {
        fakeUsers.usersDao.save(fakeUsers.ivan);
        fakeUsers.usersDao.save(fakeUsers.john);
        List<User> userList = fakeUsers.usersDao.findAll();
        int countOfMatches = 0;
        for (User user : userList) {
            String userMail = user.getEmail();
            if (userMail.equals(fakeUsers.JOHN_EMAIL) || userMail.equals(fakeUsers.IVAN_EMAIL))
                countOfMatches++;
        }
        assertEquals(countOfMatches, 2);
    }
}