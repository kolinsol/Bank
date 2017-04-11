package database.DataBaseManager;

import database.pojo.Person;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kolinsol on 4/1/17.
 */
public class ContactManager extends AbstractManager<Person.ContactInfo, Integer> {
    public ContactManager() throws SQLException {
    }

    public ArrayList<Person.ContactInfo> getAll() {
        throw new UnsupportedOperationException();
    }
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, Person.ContactInfo entity) throws SQLException {
        CallableStatement updateContactInfo = null;
        try {
            updateContactInfo = getCallableStatement("{call updateContactInfo(?,?,?,?,?)}");
            updateContactInfo.setInt("input_contact_id", id);
            updateContactInfo.setString("input_new_phone_number", entity.getPhoneNumber());
            updateContactInfo.setString("input_new_email", entity.getEmail());
            updateContactInfo.setString("input_new_address", entity.getAddress());
            updateContactInfo.setInt("input_new_city_id", new CityManager().getIdByName(entity.getCity()));
            updateContactInfo.execute();
        } finally {
            closeCallableStatement(updateContactInfo);
        }
    }

    @Override
    public Person.ContactInfo getEntityById(Integer id) {
        CallableStatement getContactInfo;
        Person.ContactInfo contactInfo = null;
        try {
            getContactInfo = getCallableStatement("{call getContactInfo(?,?,?,?,?)}");
            getContactInfo.setInt("inoutput_id", id);
            getContactInfo.registerOutParameter("inoutput_id", Types.TINYINT);
            getContactInfo.registerOutParameter("output_phone_number", Types.CHAR);
            getContactInfo.registerOutParameter("output_email", Types.VARCHAR);
            getContactInfo.registerOutParameter("output_address", Types.VARCHAR);
            getContactInfo.registerOutParameter("output_city_id", Types.TINYINT);
            getContactInfo.execute();
            int contactInfoId = getContactInfo.getInt("inoutput_id");
            String phoneNumber = getContactInfo.getString("output_phone_number");
            String email = getContactInfo.getString("output_email");
            String city = new CityManager().
                    getEntityById(getContactInfo.getInt("output_city_id"));
            String address = getContactInfo.getString("output_address");
            contactInfo = new Person.ContactInfo(email, phoneNumber, address, city);
            contactInfo.setContactInfoId(contactInfoId);
            closeCallableStatement(getContactInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }

    @Override
    public Integer create(Person.ContactInfo entity) throws SQLException {
        CallableStatement addContactInfo = null;
        Integer id;
        try {
            addContactInfo = getCallableStatement("{call addContactInfo(?,?,?,?,?)}");
            addContactInfo.setString("input_phone_number", entity.getPhoneNumber());
            addContactInfo.setString("input_email", entity.getEmail());
            addContactInfo.setString("input_address", entity.getAddress());
            addContactInfo.setInt("input_city_id", new CityManager().getIdByName(entity.getCity()));
            addContactInfo.registerOutParameter("output_id", Types.TINYINT);
            addContactInfo.execute();
            id = addContactInfo.getInt("output_id");
        } finally {
            closeCallableStatement(addContactInfo);
        }
        return id;
    }
}