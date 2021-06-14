package web.listener;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UsersDao> usersDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            usersDao = new AtomicReference<>(new UsersDaoJdbcImpl(InitDatabase.getConnection()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("usersDao", usersDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        usersDao = null;
    }
}
