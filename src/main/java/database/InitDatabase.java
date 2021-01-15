package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class InitDatabase {
    private static String tableName = "users";
    public static String pathToProperty = "/home/alim/Downloads/apache-tomcat-9.0.41/webapps/" +
            "resty_com_war/WEB-INF/classes/db.properties";

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToProperty));
            String dbUrl = properties.getProperty("db.url");
            String dbUserName = properties.getProperty("db.userName");
            String dbPassword = properties.getProperty("db.password");
            String driverName = properties.getProperty("db.driverClassName");

            Class.forName(driverName);
            return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (IOException | SQLException | ClassNotFoundException ioException) {
            throw new IllegalStateException(ioException);
        }
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
         InitDatabase.tableName = tableName;
    }

    public static String getPathToProperty() {
        return pathToProperty;
    }

    public static void setPathToProperty(String pathToProperty) {
        InitDatabase.pathToProperty = pathToProperty;
    }
}
