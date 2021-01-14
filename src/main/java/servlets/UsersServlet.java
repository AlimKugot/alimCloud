package servlets;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import helperStatic.SignUpAndIn;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    UsersDao usersDao;

    @Override
    public void init() throws ServletException {
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
//            String dbUrl = properties.getProperty("db.url");
//            String dbUserName = properties.getProperty("db.userName");
//            String dbPassword = properties.getProperty("db.password");
//            String driverName = properties.getProperty("db.driverClassName");
//
//            Class.forName(driverName);
//            Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//            usersDao = new UsersDaoJdbcImpl(connection);
//        } catch (IOException | SQLException | ClassNotFoundException ioException) {
//            throw new IllegalStateException(ioException);
//        }
        Connection connection = SignUpAndIn.initToDatabase();
        usersDao = new UsersDaoJdbcImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = usersDao.findAll();
        req.setAttribute("usersFromServer", userList);
        req.getServletContext().getRequestDispatcher("/jsp/users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
