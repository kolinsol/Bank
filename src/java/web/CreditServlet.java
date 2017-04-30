package web;

import database.DataBaseManager.CreditManager;
import database.DataBaseManager.CurrencyManager;
import database.DataBaseManager.DepositManager;
import database.pojo.Credit;
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
 * Created by kolinsol on 4/29/17.
 */
@WebServlet("/credits")
public class CreditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int personId = Integer.parseInt(req.getParameter("personId"));
        int creditTypeId = Integer.parseInt(req.getParameter("creditTypeId"));
        int currencyId = 0;
        try {
            currencyId = new CurrencyManager().getIdByName(req.getParameter("currency"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int period = Integer.parseInt(req.getParameter("period"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        String code = "C"+(int)(Math.random()*(999999 - 100000) + 100000);
        Credit credit = new Credit(code, period, personId, currencyId,
                creditTypeId, TransactionStatus.PENDING, amount);
        try {
            System.out.println(new CreditManager().create(credit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("completeMessage", "Заявка на кредит отправлена");
        req.getRequestDispatcher("jsp/main/home-page.jsp").forward(req, resp);
    }
}