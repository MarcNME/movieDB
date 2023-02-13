package moviedb.sqlService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConnector implements DbConnector {

    private Connection connection = null;
    private Statement statement = null;

    private final String url;
    private final String user;
    private final String passwd;

    public MySqlConnector(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void testConnection() {
        connect();
        
        if (connection == null) {
            System.out.println("No connection has been established!");
            System.exit(1);
        }

        try {
            excecuteNonQuery("SELECT 'Something sweet';");
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        connect();
        
        if (connection == null) {
            System.out.println("No connection has been established!");
            return null;
        }
        
        statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public void excecuteNonQuery(String dml) throws SQLException {
              
        connect();
        
        if (connection == null) {
            System.out.println("No connection has been established!");
            return;
        }
        
        statement = connection.createStatement();
        statement.execute(dml);
        close();
    }

    @Override
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
