package database.DataBaseManager;

import database.pojo.DepositType;
import database.pojo.Person;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/25/17.
 */
public class DepositTypeManager extends AbstractManager<DepositType, Integer> {

    public DepositTypeManager() throws SQLException {
    }

    @Override
    public ArrayList<DepositType> getAll() {
        ArrayList<DepositType> depositTypes = new ArrayList<>();
        ArrayList<Integer> depositTypeIds = getAllDepositTypeIds();
        if (depositTypeIds != null) {
            for(Integer depositTypeId : depositTypeIds) {
                depositTypes.add(getEntityById(depositTypeId));
            }
        }
        return depositTypes;
    }

    @Override
    public DepositType getEntityById(Integer id) {
        CallableStatement getDepositType;
        DepositType depositType = null;
        try {
            getDepositType = getCallableStatement("{call getDepositType(?,?,?,?,?,?,?)}");
            getDepositType.setInt("inoutput_id", id);
            getDepositType.registerOutParameter("inoutput_id", Types.TINYINT);
            getDepositType.registerOutParameter("output_name", Types.VARCHAR);
            getDepositType.registerOutParameter("output_percentage", Types.DOUBLE);
            getDepositType.registerOutParameter("output_min_amount", Types.DOUBLE);
            getDepositType.registerOutParameter("output_max_amount", Types.DOUBLE);
            getDepositType.registerOutParameter("output_min_period", Types.TINYINT);
            getDepositType.registerOutParameter("output_max_period", Types.TINYINT);
            getDepositType.execute();
            int depositTypeId = getDepositType.getInt("inoutput_id");
            String name = getDepositType.getString("output_name");
            double percentage = getDepositType.getDouble("output_percentage");
            double minAmount = getDepositType.getDouble("output_min_amount");
            double maxAmount = getDepositType.getDouble("output_max_amount");
            int minPeriod = getDepositType.getInt("output_min_period");
            int maxPeriod = getDepositType.getInt("output_max_period");
            depositType = new DepositType(depositTypeId, name, percentage,
                    minAmount, maxAmount, minPeriod, maxPeriod);
            closeCallableStatement(getDepositType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositType;
    }

    @Override
    public void update(Integer id, DepositType entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(DepositType entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private ArrayList<Integer> getAllDepositTypeIds() {
        CallableStatement getAllDepositTypeIds;
        ArrayList<Integer> depositTypeIds = new ArrayList<>();
        try {
            getAllDepositTypeIds = getCallableStatement("{call getAllDepositTypeIds()}");
            ResultSet rs = getAllDepositTypeIds.executeQuery();
            while (rs.next()) {
                Integer depositTypeId = rs.getInt(1);
                depositTypeIds.add(depositTypeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositTypeIds;
    }
}
