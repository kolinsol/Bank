package database.DataBaseManager;

import database.pojo.Credit;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/29/17.
 */
public class CreditManager extends AbstractManager<Credit, Integer> {
    public CreditManager() throws SQLException {
    }

    @Override
    public ArrayList<Credit> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Credit getEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, Credit entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(Credit entity) throws SQLException {
        CallableStatement createCredit;
        Integer id = null;
        try {
            createCredit = getCallableStatement("{call  addCredit(?,?,?,?,?,?,?,?)}");
            createCredit.setString("input_code", entity.getCode());
            createCredit.setInt("input_period", entity.getPeriod());
            createCredit.setInt("input_person_id", entity.getPersonId());
            createCredit.setInt("input_currency_id", entity.getCurrencyId());
            createCredit.setInt("input_credit_type_id", entity.getTransactionTypeId());
            createCredit.setString("input_status", entity.getStatus());
            createCredit.setDouble("input_amount", entity.getAmount());
            createCredit.registerOutParameter("output_id", Types.TINYINT);
            createCredit.execute();
            id = createCredit.getInt("output_id");
            closeCallableStatement(createCredit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<String[]> getPendingCredits() {
        ArrayList<String[]> records = new ArrayList<>();
        CallableStatement getPendingCredits;
        try {
            getPendingCredits = getCallableStatement("{call getPendingCredits()}");
            ResultSet rs = getPendingCredits.executeQuery();
            while (rs.next()) {
                String creditId = rs.getString("credit_id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String percentage = rs.getString("percentage");
                String amount = rs.getString("amount");
                String personId = rs.getString("person_id");
                String[] record = {
                        creditId,
                        name,
                        code,
                        percentage,
                        amount,
                        personId
                };
                records.add(record);
            }
            closeCallableStatement(getPendingCredits);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public String declineCredit(Integer id) {
        String code = null;
        CallableStatement declineCredit;
        try {
            declineCredit = getCallableStatement("{call declineCredit(?,?)}");
            declineCredit.setInt("input_credit_id", id);
            declineCredit.registerOutParameter("output_credit_code", Types.CHAR);
            declineCredit.execute();
            code = declineCredit.getString("output_credit_code");
            closeCallableStatement(declineCredit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String acceptCredit(Integer id) {
        String code = null;
        CallableStatement acceptCredit;
        try {
            acceptCredit = getCallableStatement("{call acceptCredit(?,?)}");
            acceptCredit.setInt("input_credit_id", id);
            acceptCredit.registerOutParameter("output_credit_code", Types.CHAR);
            acceptCredit.execute();
            code = acceptCredit.getString("output_credit_code");
            closeCallableStatement(acceptCredit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
}