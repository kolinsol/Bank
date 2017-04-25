package database.DataBaseManager;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/24/17.
 */
public class CurrencyManager extends AbstractManager<String, Integer> {
    public CurrencyManager() throws SQLException {
    }

    @Override
    public void update(Integer id, String entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(String entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<String> getAll() {
        ArrayList<String> currencies = new ArrayList<>();
        CallableStatement getCurrencies = null;
        try {
            getCurrencies = getCallableStatement("{call getCurrencies()}");
            ResultSet rs = getCurrencies.executeQuery();
            while (rs.next()) {
                String currency = rs.getString(1);
                currencies.add(currency);
            }
            closeCallableStatement(getCurrencies);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    public Integer getIdByName(String entity) {
        CallableStatement getCurrencyIdByName = null;
        Integer id = null;
        try {
            getCurrencyIdByName = getCallableStatement("{call getCurrencyIdByName(?,?)}");
            getCurrencyIdByName.setString("input_name", entity);
            getCurrencyIdByName.registerOutParameter("output_id", Types.TINYINT);
            getCurrencyIdByName.execute();
            id = getCurrencyIdByName.getInt("output_id");
            closeCallableStatement(getCurrencyIdByName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getEntityById(Integer id) {
        CallableStatement getCurrencyNameById = null;
        String name = null;
        try {
            getCurrencyNameById = getCallableStatement("{call getCurrencyNameById(?,?)}");
            getCurrencyNameById.setInt("input_id", id);
            getCurrencyNameById.registerOutParameter("output_name", Types.VARCHAR);
            getCurrencyNameById.execute();
            name = getCurrencyNameById.getString("output_name");
            closeCallableStatement(getCurrencyNameById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}