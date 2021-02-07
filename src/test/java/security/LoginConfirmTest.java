package security;

import fake.FakeUsers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LoginConfirmTest {
    FakeUsers fakeUsers;

    private LoginConfirmTest() {
        fakeUsers = new FakeUsers();
    }

    @Test
    void confirmEmailAndPassword() {
        fakeUsers.getUsersDao().save(fakeUsers.getIvan());
        assertTrue(LoginConfirm.confirmEmailAndPassword(fakeUsers.getIVAN_EMAIL(), fakeUsers.getIVAN_PASSWORD()));
    }
}
