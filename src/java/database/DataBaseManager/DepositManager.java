package database.DataBaseManager;

import database.pojo.Deposit;

import java.sql.CallableStatement;
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
        throw new UnsupportedOperationException();
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
            createDeposit.setInt("input_deposit_type_id", entity.getDepositTypeId());
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
}
