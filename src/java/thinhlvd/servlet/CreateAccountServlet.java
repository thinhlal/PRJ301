/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import thinhlvd.registration.RegistrationCreateError;
import thinhlvd.registration.RegistrationDAO;
import thinhlvd.registration.RegistrationDTO;
import thinhlvd.util.ApplicationConstants;

/**
 *
 * @author ThinhDuc
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    //private final String ERROR_PAGE = "createAccount.jsp";
    //private final String LOGIN_PAGE = "login.html";

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
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1.
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
        boolean foundError = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        //String url = ERROR_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.CreateAccountFeature.ERROR_PAGE);
        try {
            //2. check users errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                //errors.setUsernameLengthError("Username is required typing from 6 to 20 characters");
                errors.setUsernameLengthError(6, 20);
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                //errors.setPasswordLengthError("Password is required typing from 6 to 30 characters");
                errors.setPasswordLengthError(6, 30);
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match Password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundError = true;
                //errors.setFullNameLengthError("Fullname is required typing from 2 to 50 characters");
                errors.setFullNameLengthError(2, 50);
            }
            if (foundError) {
                //caching to specific attribute
                request.setAttribute("CREATE_ERRORS", errors);
            } else {// no errors
                //3. call MOdel - DAO (insert to DB)
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if(result){
                    //url = LOGIN_PAGE;
                    url = siteMaps.getProperty(ApplicationConstants.CreateAccountFeature.LOGIN_PAGE);
                }//create account is success
            }//no errors

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + ex.getMessage());
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }//username is existed
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
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
