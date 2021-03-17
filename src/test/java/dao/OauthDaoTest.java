package dao;

import database.InitDatabase;
import fake.FakeUsers;
import org.junit.jupiter.api.Test;

public class OauthDaoTest {
    static OauthDao oauthDao;
    FakeUsers fakeUsers = new FakeUsers();

    static {
        oauthDao = new OauthDaoJdbcImpl(InitDatabase.getConnection());
    }

    @Test
    void find() {

    }
}
