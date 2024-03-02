<%-- 
    Document   : product
    Created on : Feb 29, 2024, 2:13:02 PM
    Author     : Admin'
--%>

<%@page import="java.util.List"%>
<%@page import="thinhlvd.tbl_Product1.Tbl_Product1DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
        <%
            }
        %>
    </body>
</html>
