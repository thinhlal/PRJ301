<%-- 
    Document   : search
    Created on : Jan 30, 2024, 8:58:10 AM
    Author     : ACER
--%>

<%@page import="thinhlvd.registration.RegistrationDTO"%>
<%--<%@page session="false" %>--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%
//            Cookie[] cookies = request.getCookies();
//            if(cookies != null){
//                int count = 0;
//                for (Cookie cookie : cookies) {
//                    System.out.println("--------------");
//                    System.out.println(++count);
//                    System.out.println(cookie.getName());
//                    System.out.println(cookie.getMaxAge());
//                    System.out.println(cookie.getValue());
//                    System.out.println("--------------");
//                }
//                Cookie lastCookie = cookies[cookies.length - 1];
//                String username = lastCookie.getName();
                    RegistrationDTO user = (RegistrationDTO)session.getAttribute("USER");
                    if(user != null){
                        %> 
                        <font color="red">
                            Welcome, <%= user.getFullName() %> 
                        </font>
                <%
                    }
                %>
        <%--
            }//login is ok
        --%>
        <form action="DispatchServlet">
            <input type="submit" value="LogOut" name="btAction" />
        </form>
        <h1>Search</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue") %>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
           <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null){
                List<RegistrationDTO> result = 
                        (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if(result != null){//information is found
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for(RegistrationDTO dto : result){
                                    String urlRewriting = "DispatchServlet"
                                            + "?btAction=Delete"
                                            + "&pk=" + dto.getUsername()//delete done
                                            + "&lastSearchValue=" + searchValue;//search after deleted
                                    %>
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    <%= ++count %>
                                .</td>
                                <td>
                                    <%= dto.getUsername() %>
                                    <input type="hidden" name="txtUsername" 
                                           value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="<%= dto.getPassword() %>" />
                                </td>
                                <td>
                                    <%= dto.getFullName() %>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <%
                                               if(dto.isRole()){
                                                   %>
                                                   checked="checked"
                                           <%
                                               }//role is an admin
                                           %>
                                           />
                                </td>
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="<%= request.getParameter("txtSearchValue") %>" />
                                </td>
                            </tr>
                        </form>    
                            <%
                                }//end get each dto in result
                            %>
                        </tbody>
                    </table>

           <%
                }else { //no record is match
                    %>
                    <h2>
                        <font color = "red">
                            No record is matched!!!!
                        </font>
                    </h2>
           <%
                }//end no record is matched
            }//end search value is called second times or request from user
        %>
    </body>
</html>
