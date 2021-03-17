package dao;

import database.InitDatabase;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public abstract class CrudDaoJdbc<T> implements CrudDao<T>  {
    private static final Connection connection;
    private static final Logger logger;

    static {
        connection = InitDatabase.getConnection();
        logger = org.apache.log4j.Logger.getLogger(OauthDaoJdbcImpl.class);
        logger.setLevel(Level.ERROR);
    }

    protected Optional<T> find(Integer id, String query) {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return Optional.empty();
    }


    protected void delete(Integer id, String query) {
        if (find(id).isPresent()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }
}
