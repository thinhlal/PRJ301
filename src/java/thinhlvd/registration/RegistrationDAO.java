package thinhlvd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thinhlvd.util.DBHelper;

public class RegistrationDAO implements Serializable {
    
    public boolean checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT username "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    result = true;
                }//end username and password are verified
            }// end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()){
                    //5.1 get data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, fullName, role);
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }// accounts have not EXISTED
                    this.accounts.add(dto);
                }//end account has existed
            }// end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if(effectRows > 0){
                    result = true;
                }
            }// end connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
