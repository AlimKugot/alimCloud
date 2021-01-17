package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserSQLQueries;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoginConfirm {
    private static DataSource dataSource;

    @Autowired
    public LoginConfirm(DataSource dataSource) {
        LoginConfirm.dataSource = dataSource;
    }

    public static boolean confirmEmailAndPassword(String email, String password) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement
                    (UserSQLQueries.getSQL_DELETE_BY_EMAIL());
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return Crypto.matchesPasswords(password, hashedPassword);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return false;
    }
}
