package database.DataBaseManager;

import database.pojo.Person;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/31/17.
 */
public class PassportManager extends AbstractManager<Person.Passport, Integer> {
    public PassportManager() throws SQLException {
    }

    public ArrayList<Person.Passport> getAll() {
        throw new UnsupportedOperationException();
    }
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, Person.Passport entity) throws SQLException {
        CallableStatement updatePassport = null;
        try {
            updatePassport = getCallableStatement("{call updatePassport(?,?,?,?,?,?,?)}");
            updatePassport.setInt("input_passport_id", id);
            updatePassport.setString("input_new_serial_number", entity.getSerialNumber());
            updatePassport.setString("input_new_issue_facility", entity.getIssueFacility());
            updatePassport.setDate("input_new_issue_date", Date.valueOf(entity.getIssueDate()));
            updatePassport.setDate("input_new_expire_date", Date.valueOf(entity.getExpireDate()));
            updatePassport.setInt("input_new_city_id", new CityManager().getIdByName(entity.getCity()));
            updatePassport.setString("input_new_address", entity.getAddress());
            updatePassport.execute();
        } finally {
            closeCallableStatement(updatePassport);
        }
    }

    @Override
    public Person.Passport getEntityById(Integer id) {
        CallableStatement getPassport;
        Person.Passport passport = null;
        try {
            getPassport = getCallableStatement("{call getPassport(?,?,?,?,?,?,?)}");
            getPassport.setInt("inoutput_id", id);
            getPassport.registerOutParameter("inoutput_id", Types.TINYINT);
            getPassport.registerOutParameter("output_issue_facility", Types.VARCHAR);
            getPassport.registerOutParameter("output_issue_date", Types.DATE);
            getPassport.registerOutParameter("output_expire_date", Types.DATE);
            getPassport.registerOutParameter("output_city_id", Types.TINYINT);
            getPassport.registerOutParameter("output_address", Types.VARCHAR);
            getPassport.registerOutParameter("output_serial_number", Types.CHAR);
            getPassport.execute();
            int passportId = getPassport.getInt("inoutput_id");
            String issueFacility = getPassport.getString("output_issue_facility");
            LocalDate issueDate = getPassport.getDate("output_issue_date").toLocalDate();
            LocalDate expireDate = getPassport.getDate("output_expire_date").toLocalDate();
            String serialNumber = getPassport.getString("output_serial_number");
            String city = new CityManager().
                    getEntityById(getPassport.getInt("output_city_id"));
            String address = getPassport.getString("output_address");
            passport = new Person.Passport(serialNumber, issueFacility, issueDate,
                    expireDate, city, address);
            passport.setPassportId(passportId);
            closeCallableStatement(getPassport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passport;
    }

    @Override
    public Integer create(Person.Passport entity) throws SQLException {
        CallableStatement addPassport = null;
        Integer id;
        try {
            addPassport = getCallableStatement("{call addPassport(?,?,?,?,?,?,?)}");
            addPassport.setString("input_serial_number", entity.getSerialNumber());
            addPassport.setString("input_issue_facility", entity.getIssueFacility());
            addPassport.setDate("input_issue_date", Date.valueOf(entity.getIssueDate()));
            addPassport.setDate("input_expire_date", Date.valueOf(entity.getExpireDate()));
            addPassport.setInt("input_city_id", new CityManager().getIdByName(entity.getCity()));
            addPassport.setString("input_address", entity.getAddress());
            addPassport.registerOutParameter("output_id", Types.TINYINT);
            addPassport.execute();
            id = addPassport.getInt("output_id");
        } finally {
            closeCallableStatement(addPassport);
        }
        return id;
    }
}