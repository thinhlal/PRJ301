<%-- 
    Document   : login
    Created on : Mar 2, 2024, 5:38:30 PM
    Author     : Admin'
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="DispatchServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /> <br/>
            Password <input type="password" name="txtPassword" value="" /> <br/>
            <font style="color: red">
                ${requestScope.ERRORMSG}
                </font><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />

        </form>
        <form action="DispatchServlet">
            <input type="submit" value="Go to Shopping" name="btAction" />
        </form>
    </body>
</html>
