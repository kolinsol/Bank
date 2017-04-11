package database.DataBaseManager;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/31/17.
 */
public class CityManager extends AbstractManager<String, Integer> {

    public CityManager() throws SQLException {
    }

    @Override
    public void update(Integer id, String entity) {
        throw new UnsupportedOperationException();
    }

    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer create(String entity) {
        CallableStatement createCity = null;
        Integer id = null;
        try {
            createCity = getCallableStatement("{call  createCity(?,?)}");
            createCity.setString("input_name", entity);
            createCity.registerOutParameter("output_id", Types.TINYINT);
            createCity.execute();
            id = createCity.getInt("output_id");
            closeCallableStatement(createCity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public ArrayList<String> getAll() {
        ArrayList<String> cities = new ArrayList<>();
        CallableStatement getCities = null;
        try {
            getCities = getCallableStatement("{call getCities()}");
            ResultSet rs = getCities.executeQuery();
            while (rs.next()) {
                String city = rs.getString(1);
                cities.add(city);
            }
            closeCallableStatement(getCities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public Integer getIdByName(String entity) {
        CallableStatement getCityIdByName = null;
        Integer id = null;
        try {
            getCityIdByName = getCallableStatement("{call getCityIdByName(?,?)}");
            getCityIdByName.setString("input_name", entity);
            getCityIdByName.registerOutParameter("output_id", Types.TINYINT);
            getCityIdByName.execute();
            id = getCityIdByName.getInt("output_id");
            closeCallableStatement(getCityIdByName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getEntityById(Integer id) {
        CallableStatement getCityNameById = null;
        String name = null;
        try {
            getCityNameById = getCallableStatement("{call getCityNameById(?,?)}");
            getCityNameById.setInt("input_id", id);
            getCityNameById.registerOutParameter("output_name", Types.VARCHAR);
            getCityNameById.execute();
            name = getCityNameById.getString("output_name");
            closeCallableStatement(getCityNameById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}