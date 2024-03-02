/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import thinhlvd.tbl_Product1.Tbl_Product1DTO;

/**
 *
 * @author ACER
 */
public class CartObject implements Serializable {

    private Map<Tbl_Product1DTO, Integer> items;// ngan chua do

    public Map<Tbl_Product1DTO, Integer> getItems() {
        return items;
    }
    //k dung set vi set la up nguyen gio do vao gio cua minh

    public boolean addItemToCart(Tbl_Product1DTO product) {//bo? do` vao ngan chua do
        Tbl_Product1DTO item = product;
        boolean result = false;
        boolean checkExistedItem = false;
        //1. check valid id
        if (product == null) {
            return result;
        }
        /*if (id.trim().isEmpty()) {
            return result;
        }*/
        //2. check existed items
        if (this.items == null) {//ngan chua do khong ton tai
            this.items = new HashMap<>();
        }
        //3. check existed item
        int quantity = 1;
        /*if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }*/
        for (Tbl_Product1DTO dto : this.items.keySet()) {
            // ktra product duoc add da ton tai chua thong qua sku 
            //neu ton tai roi thi add va cho result = true(add->tang quantity success)
            if (dto.getSku().equalsIgnoreCase(item.getSku())) {
                checkExistedItem = true;
                if (dto.getQuantity() > 0 && dto.isStatus()) {//neu quantity >0 va trang thai true thi add san pham vao ngan chua do
                    dto.setQuantity(dto.getQuantity() - 1);//giam quantity di 1 khi add item
                    quantity = this.items.get(dto) + 1;
                    this.items.put(dto, quantity);//set value moi cho product do phai dung dto k dung product 
                    //vi bien references toi khac nhau mac du chung gia tri
                    result = true;
                    break;
                } else {
                    break;
                }
            }
        }//ket thuc vong lap key cua Map
        //4. drops item to items(bo do vao ngan chua do`)
        //this.items.put(id, quantity);
        if (!result && !checkExistedItem) {//ktra result = false vi` neu khong co ton tai product phai add product do vao voi quantity mac dinh = 1
            if (item.getQuantity() > 0 && item.isStatus()) {//neu quantity >0 va trang thai true thi add san pham vao ngan chua do
                item.setQuantity(item.getQuantity() - 1);//giam quantity di 1 khi add item
                this.items.put(item, quantity);
                result = true;
            }
        }
        return result;
    }

    public boolean removeItemFromCart(String sku) {
        boolean result = false;

        /*1. check existed items(ktra ngan chua do co ton tai khong items)
        if (this.items != null) {
            //2, check existed item
            if (this.items.containsKey(id)) {
                //3, remove from items
                this.items.remove(id);
                result = true;
            }//item has existed
        }//items has existed*/
        //1. check existed items(ktra ngan chua do co ton tai khong items)
        if (this.items != null) {
            //2, check existed item
            for (Tbl_Product1DTO dto : this.items.keySet()) {
                if (dto.getSku().equalsIgnoreCase(sku)) {
                    items.remove(dto);
                    result = true;
                    if (items.isEmpty()) {
                        items = null;
                    }
                    break;
                }//item has existed(Do ton tai)
            }//end traversal for finding item
        }//items has existed(Ngan chua do ton tai)
        return result;
    }
}
