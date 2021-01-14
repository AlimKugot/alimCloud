package helperStatic;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SignUpAndIn {
    public static Connection initToDatabase() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/WEB-INF/classes/db.properties"));
            String dbUrl = properties.getProperty("db.url");
            String dbUserName = properties.getProperty("db.userName");
            String dbPassword = properties.getProperty("db.password");
            String driverName = properties.getProperty("db.driverClassName");

            Class.forName(driverName);
            return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (IOException | SQLException | ClassNotFoundException ioException) {
            throw new IllegalStateException(ioException);
        }
    }

    public static String hashPasswordBcrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
        return encoder.encode(password);
    }
}