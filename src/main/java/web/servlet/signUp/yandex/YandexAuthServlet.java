package web.servlet.signUp.yandex;

import yandex.disk.YandexDisk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "YandexAuth", value = "/yandex_auth")
public class YandexAuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        YandexDisk.accessAuthorization(req, resp);
    }
}
