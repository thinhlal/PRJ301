/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thinhlvd.util.DBHelper;

/**
 *
 * @author Admin'
 */
public class OrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String productID, double unitPrice, int quantity, String totalString, String orderID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        double totalDouble = Double.parseDouble(totalString);

        try {
            //1. Make connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql
                String sql = "INSERT INTO OrderDetail (productID, unitPrice, quantity, total, orderID) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setDouble(2, unitPrice);
                stm.setInt(3, quantity);
                stm.setDouble(4, totalDouble);
                stm.setString(5, orderID);
                //4. Execute Query
                int rowsEffected = stm.executeUpdate();
                //5. Process Result
                if (rowsEffected > 0) {
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
    
    public double sumAllTotalPriceOfOrderID(String orderID) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double total = 0;

        try {
            //1. Make connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql
                String sql = "SELECT SUM(total) AS total "
                        + "FROM OrderDetail "
                        + "WHERE orderID = ? "
                        + "GROUP BY orderID";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return total;
    }
}
