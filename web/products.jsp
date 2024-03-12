<%-- 
    Document   : product
    Created on : Feb 29, 2024, 2:13:02 PM
    Author     : Admin'
--%>

<%@page import="java.util.List"%>
<%@page import="thinhlvd.tbl_Product1.Tbl_Product1DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="allProducts" value="${requestScope.PRODUCTS}"/>
        <form action="DispatchServlet">
            Choose:
            <select name="cboBook">
                <c:forEach var="product" items="${allProducts}">
                    <option value="${product.sku}">
                        ${product.name}-${product.unit_price}-${product.description}
                    </option>
                </c:forEach>
            </select>
            <font color="red">${requestScope.ADD_ERROR}</font>
            <br/>
            <input type="submit" value="Add Book To Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" /><br/>
        </form>
        <a href="login.jsp">Back to login</a><br/>
        <a href="createAccount.html">Sign Up</a>
        <%--
        <%
            List<Tbl_Product1DTO> products
                    = (List<Tbl_Product1DTO>) request.getAttribute("PRODUCTS");
            if (products != null) {
        %>
        <form action="DispatchServlet">
            Choose    
            <select name="cboBook">
                <%
                    for (Tbl_Product1DTO product : products) {
                %>
                    <option value="<%= product.getSku() %>">
                        <%= "Name:" + product.getName() + "-Price:" + product.getUnit_price()+ "-Des:" + product.getDescription() %>
                    </option>
                <%
                    }
                %>
            </select><br/>    
            <input type="submit" value="Add Book To Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" /><br/>
            <a href="login.jsp">Back to login</a>
        </form>
        <%
            }
        %>
        --%>
    </body>
</html>
