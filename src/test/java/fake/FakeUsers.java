package fake;

import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;

public class FakeUsers {
    public dao.UsersDao usersDao;
    public final String IVAN_EMAIL = "ivanNullpointerov@gmail.com";
    public final String JOHN_EMAIL = "johnNullpointerov@gmail.com";
    public User ivan;
    public User john;

    public FakeUsers() {
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
}
