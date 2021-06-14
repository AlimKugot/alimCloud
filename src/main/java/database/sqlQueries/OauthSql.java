package database.sqlQueries;

public class OauthSql {
    static String tableName = "oauth_yandex";
    static CrudDaoSql daoSql = new CrudDaoSql(tableName);

    public static String getSQL_SELECT_ALL() {
        return daoSql.getSQL_SELECT_ALL();
    }

    public static String getSQL_SELECT_BY_ID() {
        //language=SQL
        return daoSql.getSQL_SELECT_BY_ID();
    }

    public static String getSQL_DELETE_BY_ID() {
        return daoSql.getSQL_DELETE_BY_ID();
    }

    public static String getSQL_SELECT_BY_OWNER_ID() {
        //language=SQL
        return "SELECT FROM oauth_yandex WHERE owner_id = ?";
    }

    public static String getSQL_DELETE_BY_OWNER_ID() {
        //language=SQL
        return "DELETE FROM oauth_yandex WHERE owner_id = ?";
    }


    public static String getSQL_INSERT_OAUTH_TABLE() {
        //language=SQL
        return "INSERT INTO oauth_yandex " +
                "(oauth_key, owner_id, token_type, expires_in) values (?, ?, ?, ?)";
    }

    public static String getSQL_INSERT_OAUTH_TABLE_WITHOUT_OWNER_ID() {
        //language=SQL
        return "INSERT INTO oauth_yandex " +
                "(oauth_key, token_type, expires_in) values (?, ?, ?)";
    }
}
