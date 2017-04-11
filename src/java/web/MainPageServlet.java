package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kolinsol on 4/8/17.
 */
@WebServlet("/main-controller")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getParameter("update-info")) != null) {
            req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
        }
    }
}
