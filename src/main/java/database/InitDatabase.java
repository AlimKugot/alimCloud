package database;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class InitDatabase {
    private static final Logger logger = LogManager.getLogger(InitDatabase.class);
    private static boolean isChangedDbConfig = false;

    static String dbUrl, dbUserName, dbPassword, driverName;
    private static String pathToPropertyFolder;
    private static String pathDbProperty;


    static {
        String tomcatBase = System.getProperty("catalina.home");
        String projectPath = String.format("/%s/webapps/resty_com_war/", tomcatBase);
        pathToPropertyFolder = projectPath + "/WEB-INF/classes";
        pathDbProperty = pathToPropertyFolder + "/db.properties";
    }

    public static Connection getConnection() throws FileNotFoundException {
        try {
            if (!isChangedDbConfig) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(pathDbProperty));
                dbUrl = properties.getProperty("db.url");
                dbUserName = properties.getProperty("db.userName");
                dbPassword = properties.getProperty("db.password");
                driverName = properties.getProperty("db.driverClassName");
            }

            Class.forName(driverName);
            logger.info("Success connecting to Database");
            return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error(fileNotFoundException.getMessage(), fileNotFoundException);
            throw new FileNotFoundException();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    static void setDbConfiguration(String dbUrl, String dbUserName,
                                   String dbPassword, String driverName) {
        isChangedDbConfig = true;
        InitDatabase.dbUrl = dbUrl;
        InitDatabase.dbUserName = dbUserName;
        InitDatabase.dbPassword = dbPassword;
        InitDatabase.driverName = driverName;
    }

    public static String getPathToPropertyFolder() {
        return pathToPropertyFolder;
    }

    public static String getPathDbProperty() {
        return pathDbProperty;
    }

    public static void setPathDbProperty(String pathDbProperty) {
        InitDatabase.pathDbProperty = pathDbProperty;
    }

    public static void setPathToPropertyFolder(String pathToPropertyFolder) {
        InitDatabase.pathToPropertyFolder = pathToPropertyFolder;
    }
}
