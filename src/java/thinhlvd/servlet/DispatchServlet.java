/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thinhlvd.util.ApplicationConstants;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    //private final String LOGIN_PAGE = "login.html";
    //private final String LOGIN_PAGE = "";
    //private final String LOGIN_CONTROLLER = "LoginServlet";
    //private final String LOGIN_CONTROLLER = "loginController";
    //private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    //private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
    //private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    //private final String STARTUP_CONTROLLER = "StartupController";
    //private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    //private final String SHOW_ALL_PRODUCTS_CONTROLLER = "ShowAllProductsServlet";
    //private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    //private final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
    //private final String CHECK_OUT_CART_CONTROLLER = "CheckOutFromCartServlet";
    //private final String LOG_OUT_CONTROLLER = "LogOutController";
    //private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateAccountServlet";

    //private final String VIEW_YOUR_CART_PAGE = "viewCart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. get current context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        //1. Which button did user click;
        String button = request.getParameter("btAction");
        //String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);

        try {
            if (button == null) {//first time or apps starts up
                //transfer login page
                //check cookies to determine which page is tranfered
                //url = STARTUP_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.STARTUP_CONTROLLER);
            } else if (button.equals("Login")) {//user clicked Login
//                url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {//user clicked Search
//                url = SEARCH_LASTNAME_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("Delete")) {//user clicked Delete
//                url = DELETE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {//user clicked Update
//                url = UPDATE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Go to Shopping")) {//user clicked go to shopping
//                url = SHOW_ALL_PRODUCTS_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SHOW_ALL_PRODUCTS_CONTROLLER);
            } else if (button.equals("Add Book To Your Cart")) {//user clicked add item to cart
//                url = ADD_ITEM_TO_CART_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")) {//user clicked view your cart
//                url = VIEW_YOUR_CART_PAGE;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.VIEW_YOUR_CART_PAGE);
            } else if (button.equals("Remove Selected Items")) {//user clicked remove your cart
//                url = REMOVE_ITEMS_FROM_CART_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.REMOVE_ITEMS_FROM_CART_CONTROLLER);
            } else if (button.equals("CheckOut")) {//user clicked checkout your cart
//                url = CHECK_OUT_CART_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CHECK_OUT_CART_CONTROLLER);
            } else if (button.equals("LogOut")) {//user clicked checkout your cart
//                url = LOG_OUT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOG_OUT_CONTROLLER);
            } else if (button.equals("Create New Account")) {//user clicked checkout your cart
//                url = CREATE_NEW_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
            }
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
