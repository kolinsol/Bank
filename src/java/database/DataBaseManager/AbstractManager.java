package database.DataBaseManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/28/17.
 */
public abstract class AbstractManager<E,K> {
    protected Connection connect;

    public AbstractManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connect = DataBaseConnector.getConnection();
    }

    public abstract ArrayList<E> getAll();
    public abstract E getEntityById(K id);
    public abstract void update(K id, E entity) throws SQLException;
    public abstract void delete(K id);
    public abstract K create(E entity) throws SQLException;

    public CallableStatement getCallableStatement(String sql) {
        CallableStatement cs = null;
        try {
            cs = connect.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cs;
    }

    public void closeCallableStatement(CallableStatement cs) {
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}