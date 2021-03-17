package web.servlet.signUp.yandex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetOauthToken", value = "/get_token")
public class OauthServlet extends HttpServlet {
    private static final String pathToJSParser = "/jsp/oauthStringParser.jsp";
    // http://localhost:8080/get_token
    // #access_token=AgAAAABPgjvRAAbiIc_9ROE_uUUjiZll357bk8s
    // &token_type=bearer
    // &expires_in=31514584

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String access_token = req.getParameter("access_token");
        if (access_token == null || access_token.isEmpty()) {
            //parse '#' to '?'
            req.getServletContext().getRequestDispatcher(pathToJSParser).forward(req, resp);
        } else {
            String token_type = req.getParameter("token_type");
            String expires_in = req.getParameter("expires_in");
            System.out.println("Start doGet:");
            System.out.println("access_token: " + access_token);
            System.out.println("token_type: " + token_type);
            System.out.println("expires_in: " + expires_in);
            //todo: Sql.insertIntoTable("Oauth");
            //forward_to_home
        }
    }
}
