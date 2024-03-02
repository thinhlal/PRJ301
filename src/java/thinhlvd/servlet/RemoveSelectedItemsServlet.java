/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thinhlvd.cart.CartObject;
import thinhlvd.tbl_Product1.Tbl_Product1DTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RemoveSelectedItemsServlet", urlPatterns = {"/RemoveSelectedItemsServlet"})
public class RemoveSelectedItemsServlet extends HttpServlet {

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

        try {
            //1. Cust goes to his/her cart place
            HttpSession session = request.getSession(false);//tai vi co the session co the timeout o server nhung client qua tgian va chua 
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<Tbl_Product1DTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust removes item from items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null){// k xoa items nao
                            for (String selectedItem : selectedItems) {
                                cart.removeItemFromCart(selectedItem);
                            }//remove action is success
                            session.setAttribute("CART", cart);//gio hang la attribute thay doi > set
                        }//user must check atleast one item
                    }//items has existed
                }//cart has existed
            }//session has existed
        } finally {
            //refresh --> call previous function again using URL rewriting technique
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View Your Cart";//k them parameter do View Your Cart k co nhap lieu
            response.sendRedirect(urlRewriting);
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
