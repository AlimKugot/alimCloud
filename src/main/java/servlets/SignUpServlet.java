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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UsersDao usersDao;

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
        req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (userName == null || email == null || password == null) {
            //todo : write normal exception
            req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
        }

        User user = new User.Builder()
                .setUserName(userName)
                .setEmail(email)
                .setPassword(password)
                .build();
        usersDao.save(user);

        List<User> userList = usersDao.findAll();
        req.setAttribute("usersFromServer", userList);
        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }
}
