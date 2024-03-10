<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 9:06:02 AM
    Author     : ACER
--%>

<%@page import="thinhlvd.tbl_Product1.Tbl_Product1DTO"%>
<%@page import="java.util.Map"%>
<%@page import="thinhlvd.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="cart" value="${sessionScope.CART}" /><%--lay gio do(cart)--%>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" /><%--lay noi chua do(items)--%>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="key" items="${items.keySet()}" varStatus="counter"><%--lay mang keySet chua info cua do`--%>
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${key.name}</td>
                                    <td>${items.get(key)}</td><%--so luong da add cua tung mon do--%>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${key.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <c:url var="goShopping" value="DispatchServlet">
                                        <c:param name="btAction" value="Go to Shopping"/>
                                    </c:url>
                                    <a href="${goShopping}">Add more Item to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" 
                                           value="Remove Selected Items" 
                                           name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    Name<input type="text" name="txtNameViewCart" value="" /><br/>
                    Address<input type="text" name="txtAddressViewCart" value="" /><br/>
                    <input type="submit" value="CheckOut" name="btAction" />            
                </form>
            </c:if>
            <c:if test="${empty items}">
                <h2 color="red">
                    No cart is existed
                </h2>
                <c:url var="goShopping" value="DispatchServlet">
                    <c:param name="btAction" value="Go to Shopping"/>
                </c:url>
                <a href="${goShopping}">Go back to shopping</a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2 color="red">
                No cart is existed
            </h2>
            <c:url var="goShopping" value="DispatchServlet">
                <c:param name="btAction" value="Go to Shopping"/>
            </c:url>
            <a href="${goShopping}">Go back to shopping</a>
        </c:if>        
        <%--
        <h1>Book Store</h1>
        <%
            //1.Customer goes to his/her cart place
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3.Cust gets items
                    Map<Tbl_Product1DTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust shows all items
                        %>
                        <form action="DispatchServlet">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 0;
                                        for (Tbl_Product1DTO key : items.keySet()) {
                                            %>
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        .</td>
                                        <td>
                                            <%= key.getName() %>
                                        </td>
                                        <td>
                                            <%= items.get(key) %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="<%= key.getSku() %>" />
                                        </td>
                                    </tr>   
                                    <%
                                        }//traverse items
                                    %>
                                    <tr>
                                        <td colspan="3">
                                            <a href="DispatchServlet?btAction=Go to Shopping">Add more Item to Your Cart</a>
                                        </td>
                                        <td>
                                            <input type="submit" 
                                                   value="Remove Selected Items" 
                                                   name="btAction" />
                                        </td>
                                    </tr> 
                                </tbody>
                            </table>
                            Name<input type="text" name="txtNameViewCart" value="" /><br/>
                            Address<input type="text" name="txtAddressViewCart" value="" /><br/>
                            <input type="submit" value="CheckOut" name="btAction" />
                        </form>
        <%
                        return;
                    }//items have existed
                }//cart has existed
            }//session has existed
        %>

        <h2 color="red">
            No cart is existed
        </h2>
        <%
            String urlRewriting = "DispatchServlet"
                    + "?btAction=Go to Shopping";
        %>
        <a href="<%= urlRewriting %>">Go back to shopping</a>
        --%>
    </body>
</html>
