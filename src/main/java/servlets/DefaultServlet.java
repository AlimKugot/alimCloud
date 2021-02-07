package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("")
public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("AuthorizationToken") != null) {
            resp.sendRedirect(req.getRequestURI() + "/home");
            req.getServletContext().getRequestDispatcher("/home").forward(req, resp);
        } else {
            resp.sendRedirect(req.getRequestURI() + "/login");
        }
    }
}
