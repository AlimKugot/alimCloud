package database;

public class SQLQueries {
    static String tableName;
    private final String SQL_SELECT_ALL = "SELECT * FROM " + tableName;
    private final String SQL_SELECT_BY_EMAIL = "SELECT * FROM " + tableName + " WHERE email = ?";
    private final String SQL_INSERT_INTO_USERS = "INSERT INTO " + tableName +
            "(username, email, password, role) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE_BY_EMAIL = "DELETE FROM " + tableName +
            " WHERE email = ?";
    private final String SQL_UPDATE_EMAIL = "UPDATE " + tableName +
            " SET email = ? WHERE email = ?";

    static {
        tableName = InitDatabase.getTableName();
    }

    public String getSQL_SELECT_ALL() {
        return SQL_SELECT_ALL;
    }

    public String getSQL_SELECT_BY_EMAIL() {
        return SQL_SELECT_BY_EMAIL;
    }

    public String getSQL_INSERT_INTO_USERS() {
        return SQL_INSERT_INTO_USERS;
    }

    public String getSQL_DELETE_BY_EMAIL() {
        return SQL_DELETE_BY_EMAIL;
    }

    public String getSQL_UPDATE_EMAIL() {
        return SQL_UPDATE_EMAIL;
    }
}
