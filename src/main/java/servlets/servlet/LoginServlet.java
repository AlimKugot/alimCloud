package servlets.servlet;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import security.LoginConfirm;
import security.Crypto;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected AtomicReference<UsersDao> usersDao;
    private static final String login = "/jsp/login.jsp";

    @Override
    public void init() {
        usersDao = new AtomicReference<>(new UsersDaoJdbcImpl(InitDatabase.getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(login).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String servletPath = req.getServletContext().getContextPath();
        String servletUrl;
        if (LoginConfirm.confirmEmailAndPassword(email,password)) {
            servletUrl = "/home";
            HttpSession session = req.getSession();
            session.setAttribute("AuthorizationToken", Crypto.hashPasswordBcrypt(email));
        } else {
            servletUrl = "/login";
        }
        String redirect = resp.encodeRedirectURL(servletPath + servletUrl);
        resp.sendRedirect(redirect);
    }
}
