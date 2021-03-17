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
        fakeUsers.getUsersDao().save(fakeUsers.getIvan());
        assertFalse(fakeUsers.getUsersDao().
                find(fakeUsers.getIVAN_EMAIL(), fakeUsers.getIVAN_PASSWORD()).isEmpty());
    }

    @Test
    public void delete() {
        fakeUsers.getUsersDao().delete(fakeUsers.getIVAN_EMAIL());
        assertTrue(fakeUsers.getUsersDao().
                find(fakeUsers.getJOHN_EMAIL(), fakeUsers.getIVAN_PASSWORD()).isEmpty());
    }

    @Test
    public void update() {

    }

    @Test
    public void findAll() {
        fakeUsers.getUsersDao().save(fakeUsers.getIvan());
        fakeUsers.getUsersDao().save(fakeUsers.getJohn());
        List<User> userList = fakeUsers.getUsersDao().findAll();
        int countOfMatches = 0;
        for (User user : userList) {
            String userMail = user.getEmail();
            if (userMail.equals(fakeUsers.getJOHN_EMAIL())
                    || userMail.equals(fakeUsers.getIVAN_EMAIL()))
                countOfMatches++;
        }
        assertEquals(countOfMatches, 2);
    }
}