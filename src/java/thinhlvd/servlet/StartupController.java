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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thinhlvd.registration.RegistrationDAO;
import thinhlvd.registration.RegistrationDTO;
import thinhlvd.util.ApplicationConstants;

/**
 *
 * @author ACER
 */
@WebServlet(name = "StartupController", urlPatterns = {"/StartupController"})
public class StartupController extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";//welcome different user we use jsp

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
        
        String url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.LOGIN_PAGE);;
        try {
//            //1. check cookies existed
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                //2. get name and value (username and password)
//                //Cookie newestCokie = cookies[cookies.length - 1];
//                //String username = newestCokie.getName();
//                //String password = newestCokie.getValue();
//
//                //3. checkLogin (call Model)
//                RegistrationDAO dao = new RegistrationDAO();
//                RegistrationDTO dto = null;
//                for (Cookie cookie : cookies) {
//                    String username = cookie.getName();
//                    String password = cookie.getValue();
//                    dto = dao.checkLogin(username, password);
//                    if (dto != null) {
//                        url = SEARCH_PAGE;
//                        break;
//                    }
//                }
//                //4. process result
//                if (dto != null) {
//                    url = SEARCH_PAGE;
//                }//authentication is ok
            //}//no first time*/

            HttpSession session = request.getSession(false);
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user != null) {
                    String username = user.getUsername();
                    String password = user.getPassword();
                    //checkLogin (call Model)
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO dto = dao.checkLogin(username, password);
                    if (dto != null) {
                        url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.RESULT_PAGE);
                    }//authentication is ok
                }
            }// session cua nguoi dung ton tai
        } catch (SQLException ex) {
            //ex.printStackTrace();
            log("StartupController _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            //ex.printStackTrace();
            log("StartupController _ NamingException: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
