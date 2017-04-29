package database.DataBaseManager;

import database.pojo.Deposit;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/25/17.
 */
public class DepositManager extends AbstractManager<Deposit, Integer> {

    public DepositManager() throws SQLException {
    }

    @Override
    public ArrayList<Deposit> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Deposit getEntityById(Integer id) {
        CallableStatement getDeposit;
        Deposit deposit = null;
        try {
            getDeposit = getCallableStatement("{call getDeposit(?,?,?,?,?,?,?,?)}");
            getDeposit.setInt("inoutput_id", id);
            getDeposit.registerOutParameter("inoutput_id", Types.TINYINT);
            getDeposit.registerOutParameter("output_code", Types.CHAR);
            getDeposit.registerOutParameter("output_period", Types.TINYINT);
            getDeposit.registerOutParameter("output_person_id", Types.TINYINT);
            getDeposit.registerOutParameter("output_currency_id", Types.TINYINT);
            getDeposit.registerOutParameter("output_deposit_type_id", Types.TINYINT);
            getDeposit.registerOutParameter("output_status", Types.VARCHAR);
            getDeposit.registerOutParameter("output_amount", Types.DOUBLE);
            getDeposit.execute();
            Integer depositId = getDeposit.getInt("inoutput_id");
            String code = getDeposit.getString("output_code");
            Integer period = getDeposit.getInt("output_period");
            Integer personId = getDeposit.getInt("output_person_id");
            Integer currencyId = getDeposit.getInt("output_currency_id");
            Integer depositTypeId = getDeposit.getInt("output_deposit_type_id");
            String status = getDeposit.getString("output_status");
            Double amount = getDeposit.getDouble("output_amount");
            deposit = new Deposit(depositId, code, period, personId,
                    currencyId, depositTypeId, status, amount);
            closeCallableStatement(getDeposit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deposit;
    }

    @Override
    public void update(Integer id, Deposit entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(Deposit entity) throws SQLException {
        CallableStatement createDeposit;
        Integer id = null;
        try {
            createDeposit = getCallableStatement("{call  addDeposit(?,?,?,?,?,?,?,?)}");
            createDeposit.setString("input_code", entity.getCode());
            createDeposit.setInt("input_period", entity.getPeriod());
            createDeposit.setInt("input_person_id", entity.getPersonId());
            createDeposit.setInt("input_currency_id", entity.getCurrencyId());
            createDeposit.setInt("input_deposit_type_id", entity.getTransactionTypeId());
            createDeposit.setString("input_status", entity.getStatus());
            createDeposit.setDouble("input_amount", entity.getAmount());
            createDeposit.registerOutParameter("output_id", Types.TINYINT);
            createDeposit.execute();
            id = createDeposit.getInt("output_id");
            closeCallableStatement(createDeposit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<String[]> getPendingDeposits() {
        ArrayList<String[]> records = new ArrayList<>();
        CallableStatement getPendingDeposits;
        try {
            getPendingDeposits = getCallableStatement("{call getPendingDeposits()}");
            ResultSet rs = getPendingDeposits.executeQuery();
            while (rs.next()) {
                String depositId = rs.getString("deposit_id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String percentage = rs.getString("percentage");
                String amount = rs.getString("amount");
                String personId = rs.getString("person_id");
                String[] record = {
                        depositId,
                        name,
                        code,
                        percentage,
                        amount,
                        personId
                };
                records.add(record);
            }
            closeCallableStatement(getPendingDeposits);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public String declineDeposit(Integer id) {
        String code = null;
        CallableStatement declineDeposit;
        try {
            declineDeposit = getCallableStatement("{call declineDeposit(?,?)}");
            declineDeposit.setInt("input_deposit_id", id);
            declineDeposit.registerOutParameter("output_deposit_code", Types.CHAR);
            declineDeposit.execute();
            code = declineDeposit.getString("output_deposit_code");
            closeCallableStatement(declineDeposit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String acceptDeposit(Integer id) {
        String code = null;
        CallableStatement acceptDeposit;
        try {
            acceptDeposit = getCallableStatement("{call acceptDeposit(?,?)}");
            acceptDeposit.setInt("input_deposit_id", id);
            acceptDeposit.registerOutParameter("output_deposit_code", Types.CHAR);
            acceptDeposit.execute();
            code = acceptDeposit.getString("output_deposit_code");
            closeCallableStatement(acceptDeposit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
}
