<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 9:06:02 AM
    Author     : ACER
--%>

<%@page import="java.util.Map"%>
<%@page import="thinhlvd.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1.Customer goes to his/her cart place
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3.Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust shows all items
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for (String key : items.keySet()) {
                                        %>
                                <tr>
                                    <td>
                                        <%= ++count %>
                                    .</td>
                                    <td>
                                        <%= key %>
                                    </td>
                                    <td>
                                        <%= items.get(key) %>
                                    </td>
                                </tr>   
                                <%
                                    }//traverse items
                                %>
                            </tbody>
                        </table>

        <%
                        return;
                    }//items have existed
                }//cart has existed
            }//session has existed
        %>

        <h2 color="red">
            No cart is existed
        </h2>
    </body>
</html>
