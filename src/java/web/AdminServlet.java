package web;

import database.DataBaseManager.*;
import database.pojo.*;

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
        if (req.getParameter("admin-persons") != null) {
            req.setAttribute("persons", getAllPersons());
            req.getRequestDispatcher("jsp/admin/admin-persons.jsp").forward(req, resp);
        }
        if (req.getParameter("get-full-person-info") != null) {
            Integer personId = Integer.parseInt(req.getParameter("personId"));
            Person person = getPerson(personId);
            req.setAttribute("person", person);
            req.getRequestDispatcher("jsp/admin/person-full-info.jsp").forward(req, resp);
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
        if (req.getParameter("process-transactions") != null) {
            processAllTransactions();
            req.setAttribute("completeMessage", "Процедура закрытия банковского дня прошла успешно");
            req.getRequestDispatcher("jsp/admin/admin-home-page.jsp").forward(req, resp);
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
        DepositManager depositManager;
        try {
            depositManager = new DepositManager();
            deposit = depositManager.getEntityById(depositId);
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
        try {
            depositManager = new DepositManager();
            deposit = depositManager.getEntityById(depositId);
            depositManager.traceDeposit(deposit);
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
        CreditManager creditManager = null;
        try {
            creditManager = new CreditManager();
            credit = creditManager.getEntityById(creditId);
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
        try {
            creditManager = new CreditManager();
            credit = creditManager.getEntityById(creditId);
            creditManager.traceCredit(credit);
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

    private ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = null;
        try {
            persons = new PersonManager().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private Person getPerson(Integer personId) {
        Person person = null;
        try {
            person = new PersonManager().getEntityById(personId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    private void processAllTransactions() {
        try {
            new TransactionLogMananger().processAllTransactions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}