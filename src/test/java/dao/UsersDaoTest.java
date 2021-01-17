//package dao;
//
//import model.User;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { SpringTestConfiguration.class })
//public class UsersDaoTest {
//    private final User ivan;
//    private static final String IVAN_EMAIL = "ivanNullpointerov@fake.com";
//    private final User petr;
//    private static final String PETR_EMAIL = "petr@fake.com";
//
//    @Autowired
//    @Qualifier("usersDaoImpl")
//    private UsersDao usersDao;
//
//    public UsersDaoTest() {
//        ivan = new User.Builder()
//                .setUserName("Ivan")
//                .setEmail(IVAN_EMAIL)
//                .setPassword("881293434ivan")
//                .build();
//
//        petr = new User.Builder()
//                .setUserName("Petr")
//                .setEmail(PETR_EMAIL)
//                .setPassword("234891247Petr")
//                .build();
//    }
//
//    @Test
//    @Order(1)
//    public void delete() {
//        usersDao.delete(IVAN_EMAIL);
//        assertTrue(usersDao.find(IVAN_EMAIL).isEmpty());
//    }
//
//    @Test
//    @Order(2)
//    public void save() {
//        usersDao.save(ivan);
//        assertTrue(usersDao.find(IVAN_EMAIL).isPresent());
//    }
//
//    @Test
//    @Order(3)
//    public void findAll() {
//        usersDao.save(ivan);
//        usersDao.save(petr);
//        List<User> userList = usersDao.findAll();
//        int countOfMatches = 0;
//        for (User user : userList) {
//            if (user.getEmail().equals(PETR_EMAIL) || user.getEmail().equals(IVAN_EMAIL))
//                countOfMatches++;
//        }
//        assertEquals(countOfMatches, 2);
//    }
//
//    @Test
//    @Order(4)
//    public void update() {
//
//    }
//}
