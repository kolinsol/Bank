package web;

import database.DataBaseManager.AccountManager;
import database.DataBaseManager.CreditManager;
import database.DataBaseManager.DepositManager;
import database.pojo.Account;
import database.pojo.AccountType;
import database.pojo.Credit;
import database.pojo.Deposit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/26/17.
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("admin-deposits") != null) {
            req.setAttribute("deposits", getPendingDeposits());
            req.getRequestDispatcher("jsp/admin/admin-deposits.jsp").forward(req, resp);
        }
        if (req.getParameter("admin-credits") != null) {
            req.setAttribute("credits", getPendingCredits());
            req.getRequestDispatcher("jsp/admin/admin-credits.jsp").forward(req, resp);
        }
        if (req.getParameter("accept-deposit") != null) {
            Integer depositId = Integer.parseInt(req.getParameter("depositId"));
            Integer personId = Integer.parseInt(req.getParameter("personId"));
            String depositCode = acceptDeposit(depositId, personId);
            System.out.println(depositCode);
            req.setAttribute("completeMessage", "Заявка " +depositCode+ " одобрена");
            req.setAttribute("deposits", getPendingDeposits());
            req.getRequestDispatcher("jsp/admin/admin-deposits.jsp").forward(req, resp);
        }
        if (req.getParameter("decline-deposit") != null) {
            Integer depositId = Integer.parseInt(req.getParameter("depositId"));
            String depositCode = declineDeposit(depositId);
            System.out.println(depositCode);
            req.setAttribute("completeMessage", "Заявка " +depositCode+ " отклонена");
            req.setAttribute("deposits", getPendingDeposits());
            req.getRequestDispatcher("jsp/admin/admin-deposits.jsp").forward(req, resp);
        }
        if (req.getParameter("accept-credit") != null) {
            Integer creditId = Integer.parseInt(req.getParameter("creditId"));
            Integer personId = Integer.parseInt(req.getParameter("personId"));
            String creditCode = acceptCredit(creditId, personId);
            System.out.println(creditCode);
            req.setAttribute("completeMessage", "Заявка " +creditCode+ " одобрена");
            req.setAttribute("credits", getPendingCredits());
            req.getRequestDispatcher("jsp/admin/admin-credits.jsp").forward(req, resp);
        }
        if (req.getParameter("decline-credit") != null) {
            Integer creditId = Integer.parseInt(req.getParameter("creditId"));
            String creditCode = declineCredit(creditId);
            System.out.println(creditCode);
            req.setAttribute("completeMessage", "Заявка " +creditCode+ " отклонена");
            req.setAttribute("credits", getPendingCredits());
            req.getRequestDispatcher("jsp/admin/admin-credits.jsp").forward(req, resp);
        }
    }

    private ArrayList<String[]> getPendingDeposits() {
        ArrayList<String[]> records = null;
        try {
            records = new DepositManager().getPendingDeposits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    private String declineDeposit(Integer id) {
        String code = null;
        try {
            code = new DepositManager().declineDeposit(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    private ArrayList<String[]> getPendingCredits() {
        ArrayList<String[]> records = null;
        try {
            records = new CreditManager().getPendingCredits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    private String declineCredit(Integer id) {
        String code = null;
        try {
            code = new CreditManager().declineCredit(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    private String acceptDeposit(Integer depositId, Integer personId) {
        Deposit deposit = null;
        try {
            deposit = new DepositManager().getEntityById(depositId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AccountManager accountManager;
        try {
            accountManager = new AccountManager();
            Account depositAccount =
                    new Account(AccountType.DEPOSIT, deposit, personId);
            accountManager.create(depositAccount);
            Account depositPercentageAccount =
                    new Account(AccountType.DEPOSIT_PERCENTAGE, deposit, personId);
            accountManager.create(depositPercentageAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String code = null;
        try {
            code = new DepositManager().acceptDeposit(deposit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    private String acceptCredit(Integer creditId, Integer personId) {
        Credit credit = null;
        try {
            credit = new CreditManager().getEntityById(creditId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AccountManager accountManager;
        try {
            accountManager = new AccountManager();
            Account creditAccount =
                    new Account(AccountType.CREDIT, credit, personId);
            accountManager.create(creditAccount);
            Account creditPercentageAccount =
                    new Account(AccountType.CREDIT_PERCENTAGE,credit, personId);
            accountManager.create(creditPercentageAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String code = null;
        try {
            code = new CreditManager().acceptCredit(credit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
}