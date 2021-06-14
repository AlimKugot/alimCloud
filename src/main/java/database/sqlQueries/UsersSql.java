package database.sqlQueries;

public class UsersSql {
    static String tableName = "users";
    static CrudDaoSql daoSql = new CrudDaoSql(tableName);

    public static String getSQL_SELECT_ALL() {
        //language=SQL
        return daoSql.getSQL_SELECT_ALL();
    }

    public static String getSQL_SELECT_BY_ID() {
        return daoSql.getSQL_DELETE_BY_ID();
    }

    public static String getSQL_DELETE_BY_ID() {
        return daoSql.getSQL_DELETE_BY_ID();
    }

    public static String getSQL_SELECT_BY_EMAIL() {
        //language=SQL
        return "SELECT * FROM users WHERE email = ?";
    }

    public static String getSQL_INSERT_INTO_USERS() {
        //language=SQL
        return "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
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