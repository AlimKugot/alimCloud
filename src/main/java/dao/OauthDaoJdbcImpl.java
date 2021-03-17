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
    public Optional<Oauth> find(long id) {
        return super.find(id, SQL_SELECT_BY_ID);
    }

    @Override
    public void delete(long id) {
        super.delete(id, SQL_DELETE_BY_ID);
    }

    @Override
    public void save(Oauth model) {
        if (find(model.getId()).isEmpty()) {
            try (PreparedStatement ps =
                         connection.prepareStatement(SQL_INSERT_OAUTH_TABLE_WITHOUT_OWNER_ID)) {
                String token = model.getToken();
                String expiresIn = model.getExpires_in();
                String tokenType = model.getToken_type();

                ps.setString(1, token);
                ps.setString(2, tokenType);
                ps.setString(3, expiresIn);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }

    @Override
    public void save(Oauth model, long ownerId) {
        if (find(model.getId()).isEmpty()) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_OAUTH_TABLE)) {
                String token = model.getToken();
                String expiresIn = model.getExpires_in();
                String tokenType = model.getToken_type();

                ps.setString(1, token);
                ps.setLong(2, ownerId);
                ps.setString(3, tokenType);
                ps.setString(4, expiresIn);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage(), sqlException);
            }
        }
    }

    @Override
    public void update(Oauth model) {
        //todo: write update logic
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
