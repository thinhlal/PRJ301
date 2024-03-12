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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
public class LoginServlet extends HttpServlet {

    //private final String INVALID_PAGE = "invalid.html";
    //private final String INVALID_PAGE = "invalidPage";
    //private final String SEARCH_PAGE = "search.jsp";
    //private final String SEARCH_PAGE = "homePage";
    //private final String LOGIN_PAGE = "login.jsp";

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
        PrintWriter out = response.getWriter();
        //0. get current context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1. get all client information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.INVALID_PAGE);
        boolean error = false;

        try {
            //2. Call Model
            //2.1 New DAO Object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 Call method of DAO
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. process result
            if (result != null) {
                //write cookies
//                url = SEARCH_PAGE;
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 3);
//                response.addCookie(cookie);
                error = false;
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession();
                RegistrationDTO account = dao.getInfoFromUserAndPass(username, password);
                session.setAttribute("USER", account);
            } else {
                error = true;
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.LOGIN_PAGE);;
                request.setAttribute("ERRORMSG", "Incorrect UserID or Password");
            }
        } catch (NamingException ex) {
            //ex.printStackTrace();
            log("LoginServlet _ NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            //ex.printStackTrace();
            log("LoginServlet _ SQLException: " + ex.getMessage());
        } finally {
            if (error) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
//            response.sendRedirect(url);
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
