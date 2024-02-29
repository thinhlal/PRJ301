/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;// ngan chua do
    //k dung set vi set la up nguyen gio do vao gio cua minh

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id) {//bo? do` vao ngan chua do
        boolean result = false;

        //1. check valid id
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check existed item
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        //4. drops item to items(bo do vao ngan chua do`)
        this.items.put(id, quantity);
        result = true;

        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;

        //1. check existed items(ktra ngan chua do co ton tai khong items)
        if (this.items != null) {
            //2, check existed item
            if (this.items.containsKey(id)) {
                //3, remove from items
                this.items.remove(id);
                result = true;
            }//item has existed
        }//items has existed

        return result;
    }
}
