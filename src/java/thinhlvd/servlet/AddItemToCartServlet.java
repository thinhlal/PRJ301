/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
import thinhlvd.tbl_Product1.Tbl_Product1DAO;
import thinhlvd.tbl_Product1.Tbl_Product1DTO;

/**
 *
 * @author ACER
 */
@SuppressWarnings("unchecked")
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String ERROR_PAGE = "errors.html";

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
        String url = ERROR_PAGE;
        boolean result = false;
        try {
            //1.Customer goes to the cart place
            HttpSession session = request.getSession();//Gio hang o sieu thi luon luon phai co k duoc het gio hang
            //2.Customer takes his/her cart
            //call Model/DAO
            CartObject cart = (CartObject) session.getAttribute("CART"); // lay gio
            if (cart == null) {
                cart = new CartObject();// gio chua co thi keu len
            }//end cart has NOT existed
            //3.Customer drops item to his/her cart
            String item = request.getParameter("cboBook");//sku primary key
            Tbl_Product1DAO dao = new Tbl_Product1DAO();
            dao.getAllProducts();
            List<Tbl_Product1DTO> products = dao.getProducts();
            for (Tbl_Product1DTO product : products) {
                if (product.getSku().equalsIgnoreCase(item)) {//kiem tra product nao co ma sku trung voi item duoc add
                    result = cart.addItemToCart(product);//add object product voi day du thong tin san pham vao
                    break;
                }//tim thay day du ttin product co sku
            }
            if (result) {
                //thay doi cart -> setAttribute
                session.setAttribute("CART", cart);
                //4.Customer continuely take item to drop
                url = "DispatchServlet"
                        + "?btAction=Go to Shopping";
            } else {//adding item is success
                //url = OUT_OF_STOCK_PAGE;
                url = "DispatchServlet"
                        + "?btAction=Go to Shopping"; 
                request.setAttribute("ADD_ERROR", "Item is out of stock or not available now!!!!");
            }//adding item is fail(out of stock or unavailable)
        } catch (SQLException ex) {
            //ex.printStackTrace();
            log("AddItemToCartServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            //ex.printStackTrace();
            log("AddItemToCartServlet _ NamingException: " + ex.getMessage());
        } finally {
            //dung ca 2 cai nao cung duoc vi duoc luu trong session roi
            if (result) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
