package database.sqlQueries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo: disgusting tests, change them
public class CrudDaoTest {
    static CrudDaoSql daoSql;
    static String tableName = "users";

    static {
        daoSql = new CrudDaoSql(tableName);
    }

    @Test
    void selectAll() {
        //language=SQL
        String query = "SELECT * FROM users";
        assertEquals(query, daoSql.getSQL_SELECT_ALL());
    }

    @Test
    void selectByID() {
        //language=SQL
        String query = "SELECT * FROM users WHERE id = ?";
        assertEquals(query, daoSql.getSQL_SELECT_BY_ID());
    }

    @Test
    void deleteByID() {
        //language=SQL
        String query = "DELETE FROM users WHERE id = ?";
        assertEquals(query, daoSql.getSQL_DELETE_BY_ID());
    }
}
