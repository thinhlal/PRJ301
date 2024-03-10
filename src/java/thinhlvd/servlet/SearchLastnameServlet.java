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
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thinhlvd.registration.RegistrationDAO;
import thinhlvd.registration.RegistrationDTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.html";
    private final String RESULT_PAGE = "search.jsp";

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

        //1. get all client information
        String searchValue = request.getParameter("txtSearchValue");
        String url = RESULT_PAGE;

        try {
            if (!searchValue.trim().isEmpty()) {
                //2. Call Model
                //2.1 New DAO Object
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 Call method of DAO
                dao.searchLastname(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                //3. process result
                //url = RESULT_PAGE;
                request.setAttribute("SEARCH_RESULT", result);
            }//end user types valid
        } catch (SQLException ex) {
            //ex.printStackTrace();
            log("SearchLastnameServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            //ex.printStackTrace();
            log("SearchLastnameServlet _ NamingException: " + ex.getMessage());
        } finally {
            //4.send to View(+ request scope)
            //use rd because hold request object setAtt
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
