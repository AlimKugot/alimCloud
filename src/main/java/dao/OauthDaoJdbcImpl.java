package dao;

import model.Oauth;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OauthDaoJdbcImpl extends CrudDaoJdbc<Oauth> implements OauthDao {
    private final Connection connection;
    private final static Logger logger;

    static {
        logger = Logger.getLogger(OauthDaoJdbcImpl.class);
        logger.setLevel(Level.ERROR);
    }

    public OauthDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Oauth> find(Integer id) {
        return super.find(id, SQL_SELECT_BY_ID);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id, SQL_DELETE_BY_ID);
    }

    @Override
    public void save(Oauth model) {

    }

    @Override
    public void update(Oauth model) {

    }


    @Override
    public List<Oauth> findAll() {
        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            List<Oauth> oauthList = new ArrayList<>();

            while (rs.next()) {
                long id = rs.getLong("id");
                String token = rs.getString("token");
                String tokenType = rs.getString("token_type");
                String expiresIn = rs.getString("expires_in");

                Oauth oauth = new Oauth(id, token, tokenType, expiresIn);
                oauthList.add(oauth);
            }

            return oauthList;
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return null;
    }
}
