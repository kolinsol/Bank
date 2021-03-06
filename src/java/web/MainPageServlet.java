package web;

import database.DataBaseManager.CreditTypeManager;
import database.DataBaseManager.DepositTypeManager;
import database.DataBaseManager.PersonManager;
import database.pojo.Credit;
import database.pojo.CreditType;
import database.pojo.DepositType;
import database.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/8/17.
 */
@WebServlet("/main-controller")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("update-info") != null) {
            req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
        }
        if (req.getParameter("deposits") != null) {
            req.setAttribute("depositTypes", getDepositTypes());
            req.getRequestDispatcher("jsp/deposits/deposit-main.jsp").forward(req, resp);
        }
        if (req.getParameter("credits") != null) {
            req.setAttribute("creditTypes", getCreditTypes());
            req.getRequestDispatcher("jsp/credits/credit-main.jsp").forward(req, resp);
        }
        if (req.getParameter("active-transactions") != null) {
            HttpSession session = req.getSession(false);
            Person person = (Person)session.getAttribute("person");
            req.setAttribute("transactions", getActiveTransaction(person));
            req.getRequestDispatcher("jsp/transactions/active-transactions.jsp").forward(req, resp);
        }
    }

    private ArrayList<DepositType> getDepositTypes() {
        ArrayList<DepositType> depositTypes = null;
        try {
            depositTypes = new DepositTypeManager().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositTypes;
    }

    private ArrayList<CreditType> getCreditTypes() {
        ArrayList<CreditType> creditTypes = null;
        try {
            creditTypes = new CreditTypeManager().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditTypes;
    }

    private ArrayList<String[]> getActiveTransaction(Person person) {
        ArrayList<String[]> records = null;
        try {
            records = new PersonManager().getActiveTransactions(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}