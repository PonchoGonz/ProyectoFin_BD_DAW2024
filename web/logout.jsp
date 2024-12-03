<%-- 
    Document   : logout
    Created on : 7 nov 2024, 2:15:47 p.m.
    Author     : Carlos Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <%
            //Estruimos la sesión
            session.invalidate();
            
            //Redireccionamos la página de login
            response.sendRedirect("login.jsp");
        
        %>
    </body>
</html>