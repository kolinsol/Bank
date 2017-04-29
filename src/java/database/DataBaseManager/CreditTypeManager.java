package database.DataBaseManager;

import database.pojo.CreditType;
import database.pojo.DepositType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/29/17.
 */
public class CreditTypeManager extends AbstractManager<CreditType, Integer> {

    public CreditTypeManager() throws SQLException {
    }

    @Override
    public ArrayList<CreditType> getAll() {
        ArrayList<CreditType> creditTypes = new ArrayList<>();
        ArrayList<Integer> creditTypeIds = getAllCreditTypeIds();
        if (creditTypeIds != null) {
            for(Integer creditTypeId : creditTypeIds) {
                creditTypes.add(getEntityById(creditTypeId));
            }
        }
        return creditTypes;
    }

    @Override
    public CreditType getEntityById(Integer id) {
        CallableStatement getCreditType;
        CreditType creditType = null;
        try {
            getCreditType = getCallableStatement("{call getCreditType(?,?,?,?,?,?,?)}");
            getCreditType.setInt("inoutput_id", id);
            getCreditType.registerOutParameter("inoutput_id", Types.TINYINT);
            getCreditType.registerOutParameter("output_name", Types.VARCHAR);
            getCreditType.registerOutParameter("output_percentage", Types.DOUBLE);
            getCreditType.registerOutParameter("output_min_amount", Types.DOUBLE);
            getCreditType.registerOutParameter("output_max_amount", Types.DOUBLE);
            getCreditType.registerOutParameter("output_min_period", Types.TINYINT);
            getCreditType.registerOutParameter("output_max_period", Types.TINYINT);
            getCreditType.execute();
            int creditTypeId = getCreditType.getInt("inoutput_id");
            String name = getCreditType.getString("output_name");
            double percentage = getCreditType.getDouble("output_percentage");
            double minAmount = getCreditType.getDouble("output_min_amount");
            double maxAmount = getCreditType.getDouble("output_max_amount");
            int minPeriod = getCreditType.getInt("output_min_period");
            int maxPeriod = getCreditType.getInt("output_max_period");
            creditType = new CreditType(creditTypeId, name, percentage,
                    minAmount, maxAmount, minPeriod, maxPeriod);
            closeCallableStatement(getCreditType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditType;
    }

    @Override
    public void update(Integer id, CreditType entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(CreditType entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private ArrayList<Integer> getAllCreditTypeIds() {
        CallableStatement getAllCreditTypeIds;
        ArrayList<Integer> creditTypeIds = new ArrayList<>();
        try {
            getAllCreditTypeIds = getCallableStatement("{call getAllCreditTypeIds()}");
            ResultSet rs = getAllCreditTypeIds.executeQuery();
            while (rs.next()) {
                Integer creditTypeId = rs.getInt(1);
                creditTypeIds.add(creditTypeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditTypeIds;
    }
}