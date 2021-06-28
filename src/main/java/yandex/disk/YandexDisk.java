package yandex.disk;

import database.InitDatabase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class YandexDisk {
    public static String pathToProperty;

    static {
        pathToProperty = InitDatabase.getPathToPropertyFolder()
                + "/oauth_yandex.properties";
    }

    public static void accessAuthorization(HttpServletRequest req, HttpServletResponse resp) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToProperty));
            String website = properties.getProperty("website");
            String authorize = properties.getProperty("with_authorize");
            String start_args = properties.getProperty("start_args");

            String response_type = "response_type";
            String response_type_arg = properties.getProperty("response_type");
            String client_id = "client_id";
            String client_id_arg = properties.getProperty("client_id");
            String redirect_uri = "redirect_uri";
            String redirect_uri_arg = properties.getProperty("redirect_uri");

            resp.sendRedirect(website + authorize + start_args
                    + response_type + "=" + response_type_arg + "&"
                    + client_id + "=" + client_id_arg + "&"
                    + redirect_uri + "=" + redirect_uri_arg);
        } catch (IOException io) {
            throw new IllegalStateException();
        }
    }
}