<%-- 
    Document   : search
    Created on : Jan 30, 2024, 8:58:10 AM
    Author     : ACER
--%>

<%@page import="thinhlvd.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
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
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for(RegistrationDTO dto : result){
                                    String urlRewriting = "DispatchServlet"
                                            + "?btAction=delete"
                                            + "&pk=" + dto.getUsername()//delete done
                                            + "&lastSearchValue=" + searchValue;//search after deleted
                                    %>
                            <tr>
                                <td>
                                    <%= ++count %>
                                .</td>
                                <td>
                                    <%= dto.getUsername() %>
                                </td>
                                <td>
                                    <%= dto.getPassword() %>
                                </td>
                                <td>
                                    <%= dto.getFullName() %>
                                </td>
                                <td>
                                    <%= dto.isRole() %>
                                </td>
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                            </tr>        
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
