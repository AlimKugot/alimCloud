package web.listener;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UsersDao> usersDao;
    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            usersDao = new AtomicReference<>(new UsersDaoJdbcImpl(InitDatabase.getConnection()));
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("usersDao", usersDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try {
                    LOG.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    LOG.error("Error deregistering JDBC driver {}", driver, ex);
                }
            } else {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                LOG.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
            }
        }
        usersDao = null;
    }
}
