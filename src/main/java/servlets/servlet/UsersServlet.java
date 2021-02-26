package servlets.servlet;

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
import java.util.concurrent.atomic.AtomicReference;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected AtomicReference<UsersDao> usersDao;
    private static final String users = "/jsp/users.jsp";

    @Override
    public void init() {
        usersDao = new AtomicReference<>(new UsersDaoJdbcImpl(InitDatabase.getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = usersDao.get().findAll();
        req.setAttribute("usersFromServer", userList);
        req.getServletContext().getRequestDispatcher(users).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
