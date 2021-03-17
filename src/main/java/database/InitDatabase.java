package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class InitDatabase {
    private static final String pathToPropertyFolder;
    private static final String pathDbProperty;

    static {
        String tomcatBase = System.getProperty("catalina.home");
        String projectPath = String.format("/%s/webapps/resty_com_war/", tomcatBase);
        pathToPropertyFolder = projectPath + "/WEB-INF/classes";
        pathDbProperty = pathToPropertyFolder + "/db.properties";
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathDbProperty));
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

    public static String getPathToPropertyFolder() {
        return pathToPropertyFolder;
    }

    public static String getPathDbProperty() {
        return pathDbProperty;
    }
}
