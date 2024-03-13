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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thinhlvd.orderDetail.OrderDetailDAO;
import thinhlvd.orderDetail.OrderDetailDTO;
import thinhlvd.t_Order.T_OrderDAO;
import thinhlvd.t_Order.T_OrderDTO;
import thinhlvd.tbl_Product1.Tbl_Product1DAO;
import thinhlvd.tbl_Product1.Tbl_Product1DTO;
import thinhlvd.util.ApplicationConstants;

/**
 *
 * @author Admin'
 */
@WebServlet(name = "GetBillServlet", urlPatterns = {"/GetBillServlet"})
public class GetBillServlet extends HttpServlet {
    //private final String ERROR_PAGE = "errors.html";
    //private final String SHOW_BILL = "billCart.jsp";
    
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
        //0. get current context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        
        //String url = ERROR_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.GetBillFeature.ERROR_PAGE);
        
        try {
            String id = (String)request.getAttribute("IDOFORDER");//get id of T_order
            //1 Call DAO
            T_OrderDAO daoTO = new T_OrderDAO();
            T_OrderDTO getBillInfo = daoTO.getBillOfOrder(id);
            OrderDetailDAO daoOD = new OrderDetailDAO();
            daoOD.getAllItemsOrder(id);
            List<OrderDetailDTO> listItemsOrdered = daoOD.getListOrder();
            Tbl_Product1DAO daoP = new Tbl_Product1DAO();
            daoP.getAllProducts();
            List<Tbl_Product1DTO> listProductInfor = daoP.getProducts();
            
            if(getBillInfo != null && listItemsOrdered != null && listProductInfor != null){
                request.setAttribute("BILL", getBillInfo);
                request.setAttribute("LISTITEM", listItemsOrdered);
                request.setAttribute("LISTPRODUCT", listProductInfor);
                //url = SHOW_BILL;
                url = siteMaps.getProperty(ApplicationConstants.GetBillFeature.BILL_PAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("GetBillServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            ex.printStackTrace();
            log("GetBillServlet _ NamingException: " + ex.getMessage());
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
