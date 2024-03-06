/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.t_Order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import thinhlvd.util.DBHelper;

/**
 *
 * @author Admin'
 */
public class T_OrderDAO implements Serializable {

    public boolean createCheckOutOrder(String id, String date, String customer, String address, double total)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Create Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "INSERT INTO t_Order (id, date, customer, address, total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, date);
                stm.setString(3, customer);
                stm.setString(4, address);
                stm.setDouble(5, total);
                //4.Execute Query
                int effectedRows = stm.executeUpdate();
                //5.Process Result
                if (effectedRows > 0) {
                    result = true;
                }
            }
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

    /*public int countOrder()
            throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int countRows = -1;

        try {
            //1. Create Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT COUNT(id)"
                        + "AS count "
                        + "FROM t_Order";
                //3. Create Statement Object
                stm = con.createStatement();
                //4. Execute query
                rs = stm.executeQuery(sql);
                //5. Process query
                while (rs.next()) {
                    countRows = rs.getInt("count");
                }
            }
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
        return countRows;
    }*/
    public Integer getMaxOrderId() throws SQLException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        Integer result = null;

        try {
            //1 Make connection
            con = DBHelper.getConnection();
            //2 Create String sql
            String sql = "SELECT MAX(id) AS max "
                    + "FROM t_Order";
            //3 Create statement object
            stm = con.createStatement();
            //4 Execute query
            rs = stm.executeQuery(sql);
            //5 Process result
            if (rs.next()) {
                String id = rs.getString("max");
                if (id != null) {
                    String idDigits = id.substring(2);
                    result = Integer.parseInt(idDigits);
                }
            }
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

    public boolean updateTotalPrice(String orderID, String totalString)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        double totalDouble = Double.parseDouble(totalString);

        try {
            //1. Create Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE t_Order SET total = ? WHERE id = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDouble(1, totalDouble);
                stm.setString(2, orderID);
                //4. Execute query
                int effectedRows = stm.executeUpdate();
                //5. Process query
                if (effectedRows > 0) {
                    result = true;
                }
            }
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

    public T_OrderDTO getBillOfOrder(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        T_OrderDTO dto = null;
        
        try {
            //1 Make Connection
            con = DBHelper.getConnection();
            if(con != null){
                //2 Create String sql
                String sql = "SELECT date, customer, address, total "
                        + "FROM t_Order "
                        + "WHERE id = ?";
                //3 Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4 Execute Query
                rs = stm.executeQuery();
                //5 Process Result
                if(rs.next()){
                    Date date = rs.getDate("date");
                    String customer = rs.getString("customer");
                    String address = rs.getString("address");
                    double total = rs.getDouble("total");
                    dto = new T_OrderDTO(id, date, customer, address, total);
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return dto;
    }
}
