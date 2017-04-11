package database.DataBaseManager;

import database.pojo.Person;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/31/17.
 */
public class LoginManager extends AbstractManager<Person.LoginInfo, Integer> {
    public LoginManager() throws SQLException {
    }

    public ArrayList<Person.LoginInfo> getAll() {
        throw new UnsupportedOperationException();
    }
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, Person.LoginInfo entity) throws SQLException {
        CallableStatement updateLoginInfo = null;
        try {
            updateLoginInfo = getCallableStatement("{call updateLoginInfo(?,?,?)}");
            updateLoginInfo.setInt("input_login_id", id);
            updateLoginInfo.setString("input_new_username", entity.getUsername());
            updateLoginInfo.setString("input_new_password", entity.getPassword());
            updateLoginInfo.execute();
        } finally {
            closeCallableStatement(updateLoginInfo);
        }
    }

    @Override
    public Person.LoginInfo getEntityById(Integer id) {
        CallableStatement getLoginInfo;
        Person.LoginInfo loginInfo = null;
        try {
            getLoginInfo = getCallableStatement("{call getLoginInfo(?,?,?)}");
            getLoginInfo.setInt("inoutput_id", id);
            getLoginInfo.registerOutParameter("inoutput_id", Types.TINYINT);
            getLoginInfo.registerOutParameter("output_username", Types.VARCHAR);
            getLoginInfo.registerOutParameter("output_password", Types.VARCHAR);
            getLoginInfo.execute();
            int loginInfoId = getLoginInfo.getInt("inoutput_id");
            String username = getLoginInfo.getString("output_username");
            String password = getLoginInfo.getString("output_password");
            loginInfo = new Person.LoginInfo(username, password);
            loginInfo.setLoginInfoId(loginInfoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    @Override
    public Integer create(Person.LoginInfo entity) throws SQLException {
        CallableStatement addLoginInfo = null;
        Integer id;
        try {
            addLoginInfo = getCallableStatement("{call addLoginInfo(?,?,?)}");
            addLoginInfo.setString("input_username", entity.getUsername());
            addLoginInfo.setString("input_password", entity.getPassword());
            addLoginInfo.registerOutParameter("output_id", Types.TINYINT);
            addLoginInfo.execute();
            id = addLoginInfo.getInt("output_id");
        } finally {
            closeCallableStatement(addLoginInfo);
        }
        return id;
    }

    public void changePassword(String username, String newPassword) {
        CallableStatement changePassword;
        try {
            changePassword = getCallableStatement("{call changePassword(?,?)}");
            changePassword.setString("input_username", username);
            changePassword.setString("input_password", newPassword
            );
            changePassword.execute();
            closeCallableStatement(changePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkLogin(Person.LoginInfo entity) {
        CallableStatement chackLogin;
        Boolean permission = null;
        try {
            chackLogin = getCallableStatement("{call checkLogin(?,?,?)}");
            chackLogin.setString("input_username", entity.getUsername());
            chackLogin.setString("input_password", entity.getPassword());
            chackLogin.registerOutParameter("permission", Types.BOOLEAN);
            chackLogin.execute();
            permission = chackLogin.getBoolean("permission");
            closeCallableStatement(chackLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permission;
    }

    public Person getPersonByUsername(Person.LoginInfo entity) {
        CallableStatement getPersonByUsername;
        Integer personId = null;
        try {
            getPersonByUsername = getCallableStatement("{call getPersonByUsername(?,?)}");
            getPersonByUsername.setString("input_username", entity.getUsername());
            getPersonByUsername.registerOutParameter("output_person_id", Types.TINYINT);
            getPersonByUsername.execute();
            personId = getPersonByUsername.getInt("output_person_id");
            closeCallableStatement(getPersonByUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Person person = null;
        try {
            person = new PersonManager().getEntityById(personId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}