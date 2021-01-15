package servlets;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import database.LoginConfirm;
import security.Crypto;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String servletPath = req.getServletContext().getContextPath();
        String servletUrl;
        if (LoginConfirm.confirmEmailAndPassword(email,password)) {
            servletUrl = "/home";
            HttpSession session = req.getSession();
            session.setAttribute("AuthorizationToken", Crypto.hashPasswordBcrypt(email));
        } else {
            servletUrl = "/signIn";
        }
        String redirect = resp.encodeRedirectURL(servletPath + servletUrl);
        resp.sendRedirect(redirect);
    }
}
