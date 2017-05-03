package database.DataBaseManager;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kolinsol on 5/3/17.
 */
public class TransactionLogMananger extends AbstractManager<String, Integer> {
    public TransactionLogMananger() throws SQLException {
    }

    @Override
    public ArrayList<String> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, String entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(String entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Integer> getAllTransactionIds() {
        CallableStatement getAllTransactionIds;
        ArrayList<Integer> transactionIds = new ArrayList<>();
        try {
            getAllTransactionIds = getCallableStatement("{call getAllTransactionIds()}");
            ResultSet rs = getAllTransactionIds.executeQuery();
            while (rs.next()) {
                Integer transactionId = rs.getInt(1);
                transactionIds.add(transactionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionIds;
    }

    public void processTransaction(Integer transactionId) {
        CallableStatement processTransaction;
        try {
            processTransaction = getCallableStatement("{call processTransaction(?)}");
            processTransaction.setInt("input_transaction_id", transactionId);
            processTransaction.execute();
            closeCallableStatement(processTransaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processAllTransactions() {
        ArrayList<Integer> transactionIds = getAllTransactionIds();
        for (Integer transactionId : transactionIds) {
            processTransaction(transactionId);
        }
    }

    public static void main(String[] args) {
        try {
            new TransactionLogMananger().processAllTransactions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
