package dao;

import fake.FakeUsers;
import org.junit.jupiter.api.Test;

public class OauthDaoTest {
    static OauthDao oauthDao;
    FakeUsers fakeUsers = new FakeUsers();

    @Test
    void find() {
        oauthDao.find(1);
    }
}
