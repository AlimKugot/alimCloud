package servlets.servlet;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;
import security.Crypto;

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
    public void init() {
        usersDao = new AtomicReference<>(new UsersDaoJdbcImpl(InitDatabase.getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(signUp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            Cookie instruction = new Cookie("instruction", "true");
            resp.addCookie(instruction);
        }
        String contextPath = getServletContext().getContextPath();
        resp.sendRedirect( contextPath + "/first_steps");
    }
}
