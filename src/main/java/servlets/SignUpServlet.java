package servlets;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import database.InitDatabase;
import model.User;
import security.Crypto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() {
        usersDao = new UsersDaoJdbcImpl(InitDatabase.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
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
            usersDao.save(user);
            HttpSession session = req.getSession();
            session.setAttribute("AuthorizationToken", Crypto.hashPasswordBcrypt(email));
            Cookie instruction = new Cookie("instruction", "true");
            resp.addCookie(instruction);
        }
        String contextPath = getServletContext().getContextPath();
        resp.sendRedirect( contextPath + "/first_steps");
    }
}
