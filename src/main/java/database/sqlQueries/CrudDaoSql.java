package database.sqlQueries;

public class CrudDaoSql {
    String tableName;

    public CrudDaoSql(String tableName) {
        this.tableName = tableName;
    }

    public String getSQL_SELECT_ALL() {
        return "SELECT * FROM " + tableName;
    }

    public String getSQL_DELETE_BY_ID() {
        return "DELETE FROM " + tableName + " WHERE id = ?";
    }

    public String getSQL_SELECT_BY_ID() {
        return "SELECT * FROM " + tableName + " WHERE id = ?";
    }
}
