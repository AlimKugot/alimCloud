package dao;

import database.InitDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Optional;

public abstract class CrudDaoJdbc<T> implements CrudDao<T>  {
    private static Connection connection;
    private static final Logger logger = LogManager.getLogger(CrudDaoJdbc.class);

    static {
        try {
            connection = InitDatabase.getConnection();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected Optional<T> find(long id, String query) {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeQuery();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return Optional.empty();
    }


    protected void delete(long id, String query) {
        // !find.isEmpty()
        if (find(id).isPresent()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }
}
