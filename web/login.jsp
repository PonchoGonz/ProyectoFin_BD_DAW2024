<%-- 
    Document   : login
    Created on : 7 nov 2024, 2:14:20 p.m.
    Author     : Carlos Daniel
--%>

<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // No almacenar en caché
    response.setHeader("Pragma", "no-cache"); // No almacenar en caché (para navegadores antiguos)
    response.setDateHeader("Expires", 0); // Expira inmediatamente
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/styles.css">
        
       
    </head>
    <body>
        
        <section class="login">
            <div class="container mt-5">
                <div class="row">
                    <form action="ServletUsuarios" method="POST">
                    <input type="hidden" name="instruccion" value="verificarusuario">
                        <div class="form-group">
                            <label for="exampleInputUsername">Nombre de usuario</label>
                            <input type="text" class="form-control" id="USU_NOMBRE" name="USU_NOMBRE" aria-describedby="usernameHelp" placeholder="Ingresa tu nombre de usuario">

                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Constraseña</label>
                            <input type="password" class="form-control" id="USU_CONTRASENIA" name="USU_CONTRASENIA" placeholder="Password">

                        </div>
                        
                        <div class="inicio">
                            <button id="inicio" type="submit" class="btn btn-primary">Inicio</button>
                            <%
                                String mensajeDeError = (String) request.getAttribute("ERRORMENSAJE");
                                if(mensajeDeError != null){
                            %>
                        </div>
                         <label for="mensajeDeError" class="mensajeDeError"><% out.print(mensajeDeError); %></label>
                        <%
                            }
                        %>
                    </form>
                </div>
            </div>
        </section>
       
    </body>
       
    
</html>
