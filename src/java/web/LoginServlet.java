package web;

import database.DataBaseManager.CityManager;
import database.DataBaseManager.LoginManager;
import database.pojo.Person;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/4/17.
 */

@WebServlet(
        urlPatterns = {"/login"},
        initParams = {
                @WebInitParam(name = "admin-username", value = "admin"),
                @WebInitParam(name = "admin-password", value = "admin")
        })
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setCities(req, resp);
        req.setCharacterEncoding("utf-8");
        if (req.getParameter("register") != null) {
            req.getRequestDispatcher("jsp/registration/registration-step-1.jsp").forward(req, resp);
        }
        if (req.getParameter("login") != null) {
            if (checkAdmin(req, resp)) {
                req.getRequestDispatcher("jsp/admin/admin-home-page.jsp").forward(req, resp);
            } else if (checkLogin(req, resp)) {
                Person person = getEnteredPerson(req, resp);
                HttpSession session = req.getSession();
                session.setAttribute("person", person);
                req.getRequestDispatcher("jsp/main/home-page.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "Неверный логин или пароль");
                req.getRequestDispatcher("jsp/login/login.jsp").forward(req, resp);
            }
        }
    }

    private void setCities(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext sc = getServletContext();
        ArrayList<String> cities = null;
        try {
            cities = new CityManager().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.setAttribute("cities", cities);
    }

    private Boolean checkAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return ((req.getParameter("username").equals(getInitParameter("admin-username"))) &&
                (req.getParameter("password").equals(getInitParameter("admin-password"))));
    }

    private Boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Person.LoginInfo loginInfo = new Person.LoginInfo(username, password);
        Boolean result = null;
        try {
            result = new LoginManager().checkLogin(loginInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Person getEnteredPerson(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Person.LoginInfo loginInfo = new Person.LoginInfo(username, password);
        Person person = null;
        try {
            person = new LoginManager().getPersonByUsername(loginInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}