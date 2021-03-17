package dao;

import database.sqlQueries.OauthSql;
import model.Oauth;

public interface OauthDao extends CrudDao<Oauth> {
    String SQL_SELECT_ALL = OauthSql.getSQL_SELECT_ALL();
    String SQL_SELECT_BY_ID = OauthSql.getSQL_SELECT_BY_ID();
    String SQL_SELECT_BY_OWNER_ID = OauthSql.getSQL_SELECT_BY_OWNER_ID();
    String SQL_INSERT_OAUTH_TABLE = OauthSql.getSQL_INSERT_OAUTH_TABLE();
    String SQL_DELETE_BY_ID = OauthSql.getSQL_DELETE_BY_ID();
    String SQL_DELETE_BY_OWNER_ID = OauthSql.getSQL_DELETE_BY_OWNER_ID();
    String SQL_INSERT_OAUTH_TABLE_WITHOUT_OWNER_ID =
            OauthSql.getSQL_INSERT_OAUTH_TABLE_WITHOUT_OWNER_ID();

    void save(Oauth model, long ownerId);
}
