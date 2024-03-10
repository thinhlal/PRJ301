/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thinhlvd.cart.CartObject;
import thinhlvd.orderDetail.OrderDetailDAO;
import thinhlvd.t_Order.T_OrderDAO;
import thinhlvd.tbl_Product1.Tbl_Product1DTO;

/**
 *
 * @author Admin'
 */
@WebServlet(name = "CheckOutFromCartServlet", urlPatterns = {"/CheckOutFromCartServlet"})
public class CheckOutFromCartServlet extends HttpServlet {

    private String ERROR_PAGE = "errors.html";
    private String SHOW_BILL_CONTROLLER = "GetBillServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Get all parameter
        String prefixId = "HD";
        String id = "";
        String name = request.getParameter("txtNameViewCart");
        String address = request.getParameter("txtAddressViewCart");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String url = ERROR_PAGE;
        try {
            // Customer go to cart place
            HttpSession session = request.getSession(false);//gio hang co the bi timeout do o giao dien client chua refresh ma bi timeout o server
            if (session != null) {//luon luon co do trong gio moi checkout dc
                //2. Customer take his her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer get items
                    Map<Tbl_Product1DTO, Integer> items = cart.getItems();
                    if (items != null) {
                        T_OrderDAO dao_T_Order = new T_OrderDAO();
                        //lay ra id lon nhat ton tai trong db
                        Integer maxID = dao_T_Order.getMaxOrderId();
                        if (maxID == null) {//chua co t_order nao trong db(chua co id nao)
                            maxID = 1;
                        } else {
                            ++maxID;//tang them 1 neu lay ra max
                        }
                        if (maxID < 10) {
                            id = prefixId + "00" + maxID;
                        } else if (maxID >= 10 && maxID <= 99) {
                            id = prefixId + "0" + maxID;
                        } else {
                            id = prefixId + maxID;
                        }
                        //insert orderID cua 1 nguoi dung xuong DB voi total = 0
                        boolean result = dao_T_Order.createCheckOutOrder(id, dateFormat.format(date), name, address, 0.0);
                        //neu orderID insert xuong db success thi se tao ra OrderDetail
                        if (result) {//Order checkout insert success to database
                            OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
                            DecimalFormat decfor = new DecimalFormat("0.000");//format sau dau thap phan .xxx
                            //add tat ca item trong ngan chua do(items) vao db dua tren OrderID da duoc tao khi nguoi dung checkout
                            for (Tbl_Product1DTO item : items.keySet()) {//add each item to orderdetail db with orderid
                                String productID = item.getSku();
                                double unitPrice = item.getUnit_price();
                                //so luong san pham cua 1 item da add
                                int quantity = items.get(item);
                                double total = unitPrice * quantity;
                                String orderID = id;

                                boolean createOrderDetail
                                        = daoOrderDetail.createOrderDetail(productID, unitPrice, quantity, decfor.format(total), orderID);
                            }//add success all item with order id to db orderdetail
                            //update total price of orderid from orderdetail to t_order 
                            double totalOfAllItems = daoOrderDetail.sumAllTotalPriceOfOrderID(id);
                            boolean updateTotalPriceTo_T_Order = dao_T_Order.updateTotalPrice(id, decfor.format(totalOfAllItems));
                            if (updateTotalPriceTo_T_Order) {//da update total vao t_Order
                                session.removeAttribute("CART");//xoa gio hang
                                request.setAttribute("IDOFORDER", id);
                                url = SHOW_BILL_CONTROLLER;
                            }
                        }
                    }//items has existed
                }//cart has existed
            }//cart place has existed
        } catch (SQLException ex) {
            //ex.printStackTrace();
            log("CheckOutFromCartServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            //ex.printStackTrace();
            log("CheckOutFromCartServlet _ NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
