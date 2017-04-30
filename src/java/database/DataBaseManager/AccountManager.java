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
        Integer id = null;
        switch (entity.getType()) {
            case "PERSONAL":
                id = createPersonalAccount(entity);
                break;
            case "DEPOSIT":
                id = createDepositAccount(entity);
                break;
            case "DEPOSIT-PERCENTAGE":
                id = createDepositPercentageAccount(entity);
                break;
            case "CREDIT":
                id = createCreditAccount(entity);
                break;
            case "CREDIT-PERCENTAGE":
                id = createCreditPercentageAccount(entity);
                break;
        }
        return id;
    }

    private Integer createPersonalAccount(Account entity) {
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

    private Integer createDepositAccount(Account entity) {
        CallableStatement createDepositAccount;
        Integer id = null;
        try {
            createDepositAccount = getCallableStatement("{call  addDepositAccount(?,?,?,?,?,?,?)}");
            createDepositAccount.setString("input_code", entity.getCode());
            createDepositAccount.setString("input_type", entity.getType());
            createDepositAccount.setInt("input_person_id", entity.getPersonId());
            createDepositAccount.setInt("input_deposit_id", entity.getTransactionId());
            createDepositAccount.setInt("input_currency_id", entity.getCurrencyId());
            createDepositAccount.setDouble("input_amount", entity.getAmount());
            createDepositAccount.registerOutParameter("output_id", Types.TINYINT);
            createDepositAccount.execute();
            id = createDepositAccount.getInt("output_id");
            closeCallableStatement(createDepositAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private Integer createDepositPercentageAccount(Account entity) {
        CallableStatement createDepositPercentageAccount;
        Integer id = null;
        try {
            createDepositPercentageAccount = getCallableStatement("{call  addDepositPercentageAccount(?,?,?,?,?,?)}");
            createDepositPercentageAccount.setString("input_code", entity.getCode());
            createDepositPercentageAccount.setString("input_type", entity.getType());
            createDepositPercentageAccount.setInt("input_person_id", entity.getPersonId());
            createDepositPercentageAccount.setInt("input_deposit_id", entity.getTransactionId());
            createDepositPercentageAccount.setInt("input_currency_id", entity.getCurrencyId());
            createDepositPercentageAccount.registerOutParameter("output_id", Types.TINYINT);
            createDepositPercentageAccount.execute();
            id = createDepositPercentageAccount.getInt("output_id");
            closeCallableStatement(createDepositPercentageAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private Integer createCreditAccount(Account entity) {
        CallableStatement createCreditAccount;
        Integer id = null;
        try {
            createCreditAccount = getCallableStatement("{call  addCreditAccount(?,?,?,?,?,?,?)}");
            createCreditAccount.setString("input_code", entity.getCode());
            createCreditAccount.setString("input_type", entity.getType());
            createCreditAccount.setInt("input_person_id", entity.getPersonId());
            createCreditAccount.setInt("input_credit_id", entity.getTransactionId());
            createCreditAccount.setInt("input_currency_id", entity.getCurrencyId());
            createCreditAccount.setDouble("input_amount", entity.getAmount());
            createCreditAccount.registerOutParameter("output_id", Types.TINYINT);
            createCreditAccount.execute();
            id = createCreditAccount.getInt("output_id");
            closeCallableStatement(createCreditAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private Integer createCreditPercentageAccount(Account entity) {
        CallableStatement createCreditPercentageAccount;
        Integer id = null;
        try {
            createCreditPercentageAccount = getCallableStatement("{call  addCreditPercentageAccount(?,?,?,?,?,?)}");
            createCreditPercentageAccount.setString("input_code", entity.getCode());
            createCreditPercentageAccount.setString("input_type", entity.getType());
            createCreditPercentageAccount.setInt("input_person_id", entity.getPersonId());
            createCreditPercentageAccount.setInt("input_credit_id", entity.getTransactionId());
            createCreditPercentageAccount.setInt("input_currency_id", entity.getCurrencyId());
            createCreditPercentageAccount.registerOutParameter("output_id", Types.TINYINT);
            createCreditPercentageAccount.execute();
            id = createCreditPercentageAccount.getInt("output_id");
            closeCallableStatement(createCreditPercentageAccount);
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