package web.servlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class HomeServletTest {
    private static final String pathHome = "/jsp/home.jsp";
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HomeServlet servlet;

    @Test
    public void doGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        (when(req.getRequestDispatcher(pathHome))).thenReturn(dispatcher);
        servlet.doGet(req, resp);
        (verify(req, times(1))).getRequestDispatcher(pathHome);
        (verify(dispatcher)).forward(req, resp);
    }
}
