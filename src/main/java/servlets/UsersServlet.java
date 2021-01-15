package servlets;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = usersDao.findAll();
        req.setAttribute("usersFromServer", userList);
        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
