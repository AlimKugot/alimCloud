package servlets.servlet;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;
import security.Crypto;
import servlets.ContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    protected AtomicReference<UsersDao> usersDao;
    private static final String signUp = "/jsp/signUp.jsp";

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        ContextListener cs = new ContextListener();
        ServletContextEvent sce = new ServletContextEvent(this.getServletContext());
        cs.contextInitialized(sce);

        usersDao = (AtomicReference<UsersDao>) this.getServletContext().getAttribute("usersDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(signUp).forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        usersDao = (AtomicReference<UsersDao>) req.getServletContext().getAttribute("usersDao");
        String userName = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (userName != null && email != null && password != null) {
            User user = new User.Builder()
                    .userName(userName)
                    .email(email)
                    .password(password)
                    .build();
            usersDao.get().save(user);
            HttpSession session = req.getSession();
            session.setAttribute("AuthorizationToken", Crypto.hashPasswordBcrypt(email));
        }
        String contextPath = getServletContext().getContextPath();
        resp.sendRedirect( contextPath + "/first_steps");
    }
}
