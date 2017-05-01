package database.DataBaseManager;

import database.pojo.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/27/17.
 */
public class PersonManager extends AbstractManager<Person, Integer> {

    public PersonManager() throws SQLException {
    }

    public void delete(Integer id) {
        CallableStatement deletePerson;
        try {
            deletePerson = getCallableStatement("{call deletePerson(?)}");
            deletePerson.setInt("input_person_id", id);
            deletePerson.execute();
            closeCallableStatement(deletePerson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Person> getAll() {
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Integer> personIds = getAllPersonIds();
        if (personIds != null) {
            for(Integer personId : personIds) {
                persons.add(getEntityById(personId));
            }
        }
        return persons;
    }

    @Override
    public void update(Integer id, Person entity) throws SQLException {
        CallableStatement updatePerson = null;
        try {
            updatePerson = getCallableStatement("{call updatePerson(?,?,?,?,?,?,?,?)}");
            updatePerson.setInt("input_person_id", id);
            updatePerson.setString("input_new_firstname", entity.getFirstName());
            updatePerson.setString("input_new_secondname", entity.getSecondName());
            updatePerson.setString("input_new_lastname", entity.getLastName());
            updatePerson.setDate("input_new_birth_date", Date.valueOf(entity.getBirthDate()));
            updatePerson.setString("input_new_sex",entity.getSex());
            updatePerson.setBoolean("input_new_pension", entity.isPension());
            updatePerson.setBoolean("input_new_military", entity.isMilitary());
            updatePerson.execute();
        } finally {
            closeCallableStatement(updatePerson);
        }
    }

    @Override
    public Person getEntityById(Integer id) {
        CallableStatement getPerson;
        Person person = null;
        try {
            getPerson = getCallableStatement("{call getPerson(?,?,?,?,?,?,?,?)}");
            getPerson.setInt("inoutput_id", id);
            getPerson.registerOutParameter("inoutput_id", Types.TINYINT);
            getPerson.registerOutParameter("output_firstname", Types.VARCHAR);
            getPerson.registerOutParameter("output_secondname", Types.VARCHAR);
            getPerson.registerOutParameter("output_lastname", Types.VARCHAR);
            getPerson.registerOutParameter("output_birth_date", Types.DATE);
            getPerson.registerOutParameter("output_sex", Types.VARCHAR);
            getPerson.registerOutParameter("output_pension", Types.BOOLEAN);
            getPerson.registerOutParameter("output_military", Types.BOOLEAN);
            getPerson.execute();
            int personId = getPerson.getInt("inoutput_id");
            String firstname = getPerson.getString("output_firstname");
            String secondname = getPerson.getString("output_secondname");
            String lastname = getPerson.getString("output_lastname");
            LocalDate birthDate = getPerson.getDate("output_birth_date").toLocalDate();
            String sex = getPerson.getString("output_sex");
            boolean pension = getPerson.getBoolean("output_pension");
            boolean military = getPerson.getBoolean("output_military");
            person = new Person(firstname, secondname, lastname, birthDate, sex, pension, military);
            closeCallableStatement(getPerson);
            person.setPersonId(personId);
            person.setPassport(getPassport(personId));
            person.setContactInfo(getContactInfo(personId));
            person.setLoginInfo(getLoginInfo(personId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Integer create(Person entity) throws SQLException {
        CallableStatement addPerson = null;
        Integer id;
        try {
            addPerson = getCallableStatement("{call addPerson(?,?,?,?,?,?,?,?)}");
            addPerson.setString("input_firstname", entity.getFirstName());
            addPerson.setString("input_secondname", entity.getSecondName());
            addPerson.setString("input_lastname", entity.getLastName());
            addPerson.setDate("input_birth_date", Date.valueOf(entity.getBirthDate()));
            addPerson.setString("input_sex", entity.getSex());
            addPerson.setBoolean("input_pension", entity.isPension());
            addPerson.setBoolean("input_military", entity.isMilitary());
            addPerson.registerOutParameter("output_id", Types.TINYINT);
            addPerson.execute();
            id = addPerson.getInt("output_id");
        }
        finally {
            closeCallableStatement(addPerson);
        }
        return id;
    }

    public void setPassport(Integer personId, Integer passportId) {
        CallableStatement setPassport;
        try {
            setPassport = getCallableStatement("{call setPassport(?,?)}");
            setPassport.setInt("input_person_id", personId);
            setPassport.setInt("input_passport_id", passportId);
            setPassport.execute();
            closeCallableStatement(setPassport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setContactInfo(Integer personId, Integer contactId) {
        CallableStatement setContactInfo;
        try {
            setContactInfo = getCallableStatement("{call setContactInfo(?,?)}");
            setContactInfo.setInt("input_person_id", personId);
            setContactInfo.setInt("input_contact_id", contactId);
            setContactInfo.execute();
            closeCallableStatement(setContactInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLoginInfo(Integer personId, Integer loginId) {
        CallableStatement setLoginInfo;
        try {
            setLoginInfo = getCallableStatement("{call setLoginInfo(?,?)}");
            setLoginInfo.setInt("input_person_id", personId);
            setLoginInfo.setInt("input_login_id", loginId);
            setLoginInfo.execute();
            closeCallableStatement(setLoginInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person.Passport getPassport(Integer personId) {
        CallableStatement getPassportId;
        Integer passportId = null;
        Person.Passport passport = null;
        try {
            getPassportId = getCallableStatement("{call getPassportId(?,?)}");
            getPassportId.setInt("input_person_id", personId);
            getPassportId.registerOutParameter("output_passport_id", Types.TINYINT);
            getPassportId.execute();
            passportId = getPassportId.getInt("output_passport_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (passportId != null) {
            try {
                passport = new PassportManager().getEntityById(passportId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException();
        }
        return passport;
    }

    private Person.ContactInfo getContactInfo(Integer personId) {
        CallableStatement getContactInfoId;
        Integer contactInfoId = null;
        Person.ContactInfo contactInfo = null;
        try {
            getContactInfoId = getCallableStatement("{call getContactInfoId(?,?)}");
            getContactInfoId.setInt("input_person_id", personId);
            getContactInfoId.registerOutParameter("output_contact_id", Types.TINYINT);
            getContactInfoId.execute();
            contactInfoId = getContactInfoId.getInt("output_contact_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (contactInfoId != null) {
            try {
                contactInfo = new ContactManager().getEntityById(contactInfoId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException();
        }
        return contactInfo;
    }

    private Person.LoginInfo getLoginInfo(Integer personId) {
        CallableStatement getLoginInfoId;
        Integer loginInfoId = null;
        Person.LoginInfo loginInfo = null;
        try {
            getLoginInfoId = getCallableStatement("{call getLoginInfoId(?,?)}");
            getLoginInfoId.setInt("input_person_id", personId);
            getLoginInfoId.registerOutParameter("output_login_id", Types.TINYINT);
            getLoginInfoId.execute();
            loginInfoId = getLoginInfoId.getInt("output_login_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (loginInfoId != null) {
            try {
                loginInfo = new LoginManager().getEntityById(loginInfoId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException();
        }
        return loginInfo;
    }

    private ArrayList<Integer> getAllPersonIds() {
        CallableStatement getAllPersonIds;
        ArrayList<Integer> personIds = new ArrayList<>();
        try {
            getAllPersonIds = getCallableStatement("{call getAllPersonIds()}");
            ResultSet rs = getAllPersonIds.executeQuery();
            while (rs.next()) {
                Integer personId = rs.getInt(1);
                personIds.add(personId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personIds;
    }

    public ArrayList<String[]> getActiveTransactions(Person person) {
        ArrayList<String[]> records = new ArrayList<>();
        CallableStatement getActivePersonalTransactions;
        try {
            getActivePersonalTransactions = getCallableStatement("{call getActivePersonalTransactions(?)}");
            getActivePersonalTransactions.setInt("input_person_id", person.getPersonId());
            ResultSet rs = getActivePersonalTransactions.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String code = rs.getString("code");
                String amount = rs.getString("amount");
                String percentage = rs.getString("percentage");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String[] record = {
                        name,
                        code,
                        amount,
                        percentage,
                        startDate,
                        endDate
                };
                records.add(record);
            }
            closeCallableStatement(getActivePersonalTransactions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}