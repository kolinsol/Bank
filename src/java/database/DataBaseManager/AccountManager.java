package database.DataBaseManager;

import database.pojo.Account;
import database.pojo.AccountType;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/24/17.
 */
public class AccountManager extends AbstractManager<Account, Integer> {
    public AccountManager() throws SQLException {
    }

    @Override
    public void update(Integer id, Account entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(Account entity) throws SQLException {
        CallableStatement createPersonalAccount;
        Integer id = null;
        try {
            createPersonalAccount = getCallableStatement("{call  addPersonalAccount(?,?,?,?)}");
            createPersonalAccount.setString("input_code", entity.getCode());
            createPersonalAccount.setString("input_type", entity.getType());
            createPersonalAccount.setInt("input_person_id", entity.getPersonId());
            createPersonalAccount.registerOutParameter("output_id", Types.TINYINT);
            createPersonalAccount.execute();
            id = createPersonalAccount.getInt("output_id");
            closeCallableStatement(createPersonalAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Account getEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Account> getAll() {
        throw new UnsupportedOperationException();
    }
}
