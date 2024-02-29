/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_CONTROLLER = "StartupController";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String SHOW_ALL_PRODUCTS_CONTROLLER = "ShowAllProductsServlet";
    //private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String GET_INFO_PRODUCT_BEFORE_ADD_ITEM_CONTROLLER = "GetAllInfoProductsBeforeAddItem";

    private final String VIEW_YOUR_CART_PAGE = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //1. Which button did user click;
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;

        try {
            if (button == null) {//first time or apps starts up
                //transfer login page
                //check cookies to determine which page is tranfered
                url = STARTUP_CONTROLLER;
            } else if (button.equals("Login")) {//user clicked Login
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {//user clicked Search
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Delete")) {//user clicked Delete
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {//user clicked Update
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Go to Shopping")) {//user clicked go to shopping
                url = SHOW_ALL_PRODUCTS_CONTROLLER;
            } else if (button.equals("Add Book To Your Cart")) {//user clicked add item to cart
                url = GET_INFO_PRODUCT_BEFORE_ADD_ITEM_CONTROLLER;
            } else if (button.equals("View Your Cart")) {//user clicked view your cart
                url = VIEW_YOUR_CART_PAGE;
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
