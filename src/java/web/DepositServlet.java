package web;

import database.DataBaseManager.CurrencyManager;
import database.DataBaseManager.DepositManager;
import database.pojo.Deposit;
import database.pojo.TransactionStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by kolinsol on 4/25/17.
 */
@WebServlet("/deposits")
public class DepositServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int personId = Integer.parseInt(req.getParameter("personId"));
        int depositTypeId = Integer.parseInt(req.getParameter("depositTypeId"));
        int currencyId = 0;
        try {
            currencyId = new CurrencyManager().getIdByName(req.getParameter("currency"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int period = Integer.parseInt(req.getParameter("period"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        String code = "D"+(int)(Math.random()*(999999 - 100000) + 100000);
        Deposit deposit = new Deposit(code, period, personId, currencyId,
                depositTypeId, TransactionStatus.PENDING, amount);
        try {
            System.out.println(new DepositManager().create(deposit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("completeMessage", "Заявка на депозит отправлена");
        req.getRequestDispatcher("jsp/main/home-page.jsp").forward(req, resp);
    }
}