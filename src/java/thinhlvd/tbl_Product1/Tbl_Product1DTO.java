/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.tbl_Product1;

import java.io.Serializable;

/**
 *
 * @author Admin'
 */
public class Tbl_Product1DTO implements Serializable{
    private String sku;
    private String name;
    private String description;
    private double unit_price;
    private int quantity;
    private boolean status;

    public Tbl_Product1DTO() {
    }

    public Tbl_Product1DTO(String sku, String name, String description, double unit_price, int quantity, boolean status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.status = status;
    }
    
    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the unit_price
     */
    public double getUnit_price() {
        return unit_price;
    }

    /**
     * @param unit_price the unit_price to set
     */
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
