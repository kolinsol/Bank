package web;

import database.DataBaseManager.DepositManager;

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
        if (req.getParameter("accept-deposit") != null) {
            System.out.println(req.getParameter("depositId"));
        }
        if (req.getParameter("decline-deposit") != null) {
            Integer depositId = Integer.parseInt(req.getParameter("depositId"));
            String depositCode = declineDeposit(depositId);
            System.out.println(depositCode);
            req.setAttribute("completeMessage", "Заявка " +depositCode+ " отклонена");
            req.setAttribute("deposits", getPendingDeposits());
            req.getRequestDispatcher("jsp/admin/admin-deposits.jsp").forward(req, resp);
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
}
