package repository;

public class UserSQLQueries {

    public static String getSQL_SELECT_ALL() {
        //language=SQL
        return "SELECT * FROM users";
    }

    public static String getSQL_SELECT_BY_EMAIL() {
        //language=SQL
        return "SELECT * FROM users WHERE email = ?";
    }

    public static String getSQL_INSERT_INTO_USERS() {
        //language=SQL
        return "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    }

    public static String getSQL_DELETE_BY_EMAIL() {
        //language=SQL
        return "DELETE FROM users WHERE email = ?";
    }

    public static String getSQL_UPDATE_EMAIL() {
        //language=SQL
        return "UPDATE users SET email = ? WHERE email = ?";
    }

}
