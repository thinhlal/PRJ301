
package thinhlvd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static Connection getConnection() 
        throws ClassNotFoundException, SQLException {
        //1. Load driver (Driver is available)
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create Connection String
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName = Registration;"
                + "instanceName = THINHDUC";
        //3. Open Connection
        Connection con = DriverManager.getConnection(url, "sa", "123");
        
        return con;
    }
}
