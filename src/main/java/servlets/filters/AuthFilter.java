package servlets.filters;

import dao.UsersDao;
import model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@WebFilter(
        urlPatterns = {"/home/*", "/admin/*"},
        filterName = "authentication"
)
public class AuthFilter implements Filter {
    private static final String adminPage = "/jsp/adminHome.jsp";
    private static final String homePage = "/jsp/home.jsp";
    private static final String loginPage = "/jsp/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession(false);

        @SuppressWarnings("unchecked")
        final AtomicReference<UsersDao> usersDao =
                (AtomicReference<UsersDao>) req.getServletContext().getAttribute("usersDao");

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final Role role = (Role) session.getAttribute("role");

            moveToMenu(req, resp, role);
        } else if (usersDao.get().find(login, password).isPresent()) {

            final Role role = usersDao.get().find(login, password).get().getRole();

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, resp, Role.user);
        } else {
            moveToMenu(req, resp, Role.unknown);
        }
        filterChain.doFilter(req, resp);
    }

    /*
    If user exists move to home page
    If user is admin move to admin page
    If user is unknown move to login page
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse resp,
                            final Role role)
            throws ServletException, IOException {

        if (role.equals(Role.admin)) {
            req.getRequestDispatcher(adminPage).forward(req, resp);
        } else if (role.equals(Role.user)) {
            req.getRequestDispatcher(homePage).forward(req, resp);
        } else {
            req.getRequestDispatcher(loginPage).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
