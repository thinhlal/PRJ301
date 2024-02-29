/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.tbl_Product1;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thinhlvd.util.DBHelper;

/**
 *
 * @author Admin'
 */
public class tbl_Product1DAO implements Serializable{
    private List<tbl_Product1DTO> products;

    public List<tbl_Product1DTO> getProducts() {
        return products;
    }
    
    public void getAllProducts() 
            throws SQLException, NamingException{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            //1. make Connection
            con = DBHelper.getConnection();
            if(con != null){
                //2. Create String sql
                String sql = "SELECT sku, name, description, unit_price, quantity, status "
                        + "FROM tbl_Product1";
                //3. Create Statement object
                stm = con.createStatement();
                //4. Execute Query
                rs = stm.executeQuery(sql);
                //5. process result
                while(rs.next()){
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unit_price = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    tbl_Product1DTO dto = new tbl_Product1DTO(sku, name, description, unit_price, quantity, status);
                    if(this.products == null){
                        products = new ArrayList<>();
                    }
                    products.add(dto);
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
    }
}
