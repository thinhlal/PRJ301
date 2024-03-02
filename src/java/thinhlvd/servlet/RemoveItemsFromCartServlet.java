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
 * @author Admin'
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

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
            //1. Customer Go to Cart place
            HttpSession session = request.getSession(false);//tai vi session co the time out cho du` client chua thoat man hinh khi ngta dong may
            if (session != null) {
                //2. Customer take his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null){//neu co gio hang thi xoa
                    //3. Customer get items(khach hang lay noi chua do`)
                    Map<Tbl_Product1DTO, Integer> items = cart.getItems();
                    if(items != null){//neu ngan chua do` co ton tai thi xoa
                        //4. Customer removes item from items
                        String[] removedItems = request.getParameterValues("chkItem");
                        if(removedItems != null){// co the k xoa item nao
                            for (String removedItem : removedItems) {
                                cart.removeItemFromCart(removedItem);
                            }
                            //gio hang(attribute thay doi)>set
                            session.setAttribute("CART", cart);
                        }//At least 1 item has removed
                    }//items has existed(ngan chua do` ton tai)
                }//Cart has existed(gio hang ton tai)
            }//cart place has existed(noi chua gio hang ton` tai)
        } finally {
            //refresh --> call previous function again using urlRewriting technique
            //dung urlRewriting vi trung btAction o trang product.jsp
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View Your Cart";//k them parameter vi` view your cart k co nhap lieu
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
