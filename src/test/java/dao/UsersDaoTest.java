package dao;

import fake.FakeUsers;
import model.User;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDaoTest {
    FakeUsers fakeUsers = new FakeUsers();
    UsersDao usersDao = fakeUsers.getUsersDao();

    @Test
    public void save() {
        usersDao.save(fakeUsers.getIvan());
        assertTrue(usersDao.find
                (fakeUsers.getIVAN_EMAIL(), fakeUsers.getIVAN_PASSWORD()).isPresent());
    }

    @Test
    public void delete() {
        usersDao.delete(fakeUsers.getIVAN_EMAIL());
        assertTrue(usersDao.
                find(fakeUsers.getJOHN_EMAIL(), fakeUsers.getIVAN_PASSWORD()).isEmpty());
    }

    @Test
    public void update() {

    }

    @Test
    public void findAll() {
        usersDao.save(fakeUsers.getIvan());
        usersDao.save(fakeUsers.getJohn());
        List<User> userList = usersDao.findAll();
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