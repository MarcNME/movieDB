package moviedb.sqlService;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConnector {
    void connect();
    ResultSet executeQuery(String sql) throws SQLException;
    void excecuteNonQuery(String dml) throws SQLException;
    void testConnection();
    void close();
}
