
package thinhlvd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {
    public static Connection getConnection() 
        throws /*ClassNotFoundException,*/ SQLException, NamingException {
        
        //1.Get current context
        Context currentContext = new InitialContext();
        //2.Lookup tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3.Lookup DS
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        
        //4.Open connection DS
        Connection con = ds.getConnection();
        
        return con;
        
//        //1. Load driver (Driver is available)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"
//                + "localhost:1433;"
//                + "databaseName = Registration;"
//                + "instanceName = THINHDUC";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123");
//        
//        return con;
//        
    }
}
