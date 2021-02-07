package fake;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;

public class FakeUsers {
    private dao.UsersDao usersDao;
    private String IVAN_EMAIL = "ivanNullpointerov@gmail.com";
    private String JOHN_EMAIL = "johnNullpointerov@gmail.com";
    private String IVAN_PASSWORD = "881293434ivan";
    private String JOHN_PASSWORD = "58274591john";
    private User ivan;
    private User john;

    public FakeUsers() {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
        usersDao.delete(IVAN_EMAIL);
        usersDao.delete(JOHN_EMAIL);
        ivan = new User.Builder()
                .userName("Ivan")
                .email(IVAN_EMAIL)
                .password(IVAN_PASSWORD)
                .build();
        john = new User.Builder()
                .userName("john")
                .email(JOHN_EMAIL)
                .password(JOHN_PASSWORD)
                .build();
        usersDao.save(ivan);
        usersDao.save(john);
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public String getIVAN_EMAIL() {
        return IVAN_EMAIL;
    }

    public void setIVAN_EMAIL(String IVAN_EMAIL) {
        this.IVAN_EMAIL = IVAN_EMAIL;
    }

    public String getJOHN_EMAIL() {
        return JOHN_EMAIL;
    }

    public void setJOHN_EMAIL(String JOHN_EMAIL) {
        this.JOHN_EMAIL = JOHN_EMAIL;
    }

    public User getIvan() {
        return ivan;
    }

    public void setIvan(User ivan) {
        this.ivan = ivan;
    }

    public User getJohn() {
        return john;
    }

    public void setJohn(User john) {
        this.john = john;
    }

    public String getIVAN_PASSWORD() {
        return IVAN_PASSWORD;
    }

    public void setIVAN_PASSWORD(String IVAN_PASSWORD) {
        this.IVAN_PASSWORD = IVAN_PASSWORD;
    }

    public String getJOHN_PASSWORD() {
        return JOHN_PASSWORD;
    }

    public void setJOHN_PASSWORD(String JOHN_PASSWORD) {
        this.JOHN_PASSWORD = JOHN_PASSWORD;
    }
}
