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

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT lastname, isAdmin "
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
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, lastname, isAdmin);
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
    
    public RegistrationDTO getInfoFromUserAndPass(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT lastname, isAdmin "
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
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, password, lastname, isAdmin);
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
                while (rs.next()) {
                    //5.1 get data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, fullName, role);
                    if (this.accounts == null) {
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
                if (effectRows > 0) {
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

    public boolean updateAccount(String username, String password, String chkAdmin)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql;
                if (username != null) {
                    if (chkAdmin != null) {
                        chkAdmin = "true";
                    } else {
                        chkAdmin = "false";
                    }
                    if (password != null) {
                        sql = "UPDATE Registration "
                                + "SET password = ?, isAdmin = ? "
                                + "WHERE username = ?";
                        //3. create Statement Object
                        stm = con.prepareStatement(sql);
                        stm.setString(1, password);
                        stm.setString(2, chkAdmin);
                        stm.setString(3, username);
                    } else {
                        sql = "UPDATE Registration "
                                + "SET isAdmin = ? "
                                + "WHERE username = ?";
                        //3. create Statement Object
                        stm = con.prepareStatement(sql);
                        stm.setString(1, chkAdmin);
                        stm.setString(2, username);
                    }
                }
                //4. Execute Query
                int effectRows = 0;
                if (stm != null) {
                    effectRows = stm.executeUpdate();
                }
                //5. Process Result
                if (effectRows > 0) {
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
