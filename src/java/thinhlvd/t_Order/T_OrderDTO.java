/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.t_Order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin'
 */
public class T_OrderDTO implements Serializable{
    private String id;
    private Date datetime;
    private String customer;
    private String address;
    private double total;

    public T_OrderDTO() {
    }

    public T_OrderDTO(String id, Date datetime, String customer, String address, double total) {
        this.id = id;
        this.datetime = datetime;
        this.customer = customer;
        this.address = address;
        this.total = total;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
