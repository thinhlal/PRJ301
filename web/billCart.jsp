<%-- 
    Document   : billCart
    Created on : Mar 3, 2024, 5:54:48 PM
    Author     : Admin'
--%>

<%@page import="thinhlvd.tbl_Product1.Tbl_Product1DTO"%>
<%@page import="thinhlvd.orderDetail.OrderDetailDTO"%>
<%@page import="java.util.List"%>
<%@page import="thinhlvd.t_Order.T_OrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
    </head>
    <body>
        <h1>Your Bill</h1>
        <%
            T_OrderDTO orderID = (T_OrderDTO)request.getAttribute("BILL");
            List<OrderDetailDTO> orderDetails = (List<OrderDetailDTO>)request.getAttribute("LISTITEM");
            List<Tbl_Product1DTO> listProductInfor = (List<Tbl_Product1DTO>)request.getAttribute("LISTPRODUCT");
            System.out.println(orderDetails);
            if(orderID != null && orderDetails != null && listProductInfor != null){
                int count = 0;
                %>
                <b>Name: <%= orderID.getCustomer() %></b><br/>
                <b>Address <%= orderID.getAddress()%></b><br/>
                <b>Date: <%= orderID.getDatetime()%></b><br/>
                <br/>
                <br/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                            <% 
                                for(OrderDetailDTO orderD : orderDetails){//Duyet tung san pham da duoc order
                                    if(orderD.getOrderID().equals(orderID.getId())){//ktra san pham nao co id cua user nay
                                        for(Tbl_Product1DTO product :listProductInfor){//lay name product dua tren id user da duoc duyet trong tung san pham trong order detail
                                            if(product.getSku().equals(orderD.getProductID())){
                            %>
                                                <tr>
                                                    <td><%= ++count %></td>
                                                    <td><%= product.getName() %></td>
                                                    <td><%= product.getDescription() %></td>
                                                    <td><%= product.getUnit_price() %></td>
                                                    <td><%= orderD.getQuantity() %></td>
                                                    <td><%= orderD.getTotal() %></td>
                                                </tr>
                            <%
                                            }
                                        }
                                    }
                                }
                            %>
                            <tr>
                                <td colspan="6" style="text-align: right; color: dodgerblue">
                                    Grand Total: <%= orderID.getTotal() %>
                                </td>
                            </tr>
                    </tbody>
                </table>
                                
        <%
            }
            String urlRewriting = "DispatchServlet"
                    + "?btAction=Go to Shopping";
        %>
        <br/>
        <br/>
        <a href="<%= urlRewriting %>">Go back to shopping</a><br/>
        <a href="login.jsp">Go to login</a>
    </body>
</html>
