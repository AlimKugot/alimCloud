package web.servlet;

import dao.UsersDao;
import security.Crypto;
import web.listener.ContextListener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    private static AtomicReference<UsersDao> usersDao;
    private static final String pathLogin = "/jsp/login.jsp";

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        ContextListener cs = new ContextListener();
        ServletContextEvent sce = new ServletContextEvent(this.getServletContext());
        cs.contextInitialized(sce);

        usersDao = (AtomicReference<UsersDao>)
                this.getServletContext().getAttribute("usersDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(pathLogin).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String servletPath = req.getServletContext().getContextPath();
        String servletUrl;
        if (usersDao.get().find(email, password).isPresent()) {
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
