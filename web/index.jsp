<%@ page language="java" import="java.util.*, com.pintura.campusjalpa.*" contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // No almacenar en caché
    response.setHeader("Pragma", "no-cache"); // No almacenar en caché (para navegadores antiguos)
    response.setDateHeader("Expires", 0); // Expira inmediatamente
    
    // Verificamos si el usuario ya proporcionó sus credenciales
    String uid = (String) session.getAttribute("session");

    // Si la variable uid es nula, quiere decir que el usuario no se ha logeado
    if (uid == null) {
        // Redirigir al login si no está logeado
        response.sendRedirect("login.jsp");
        return; // Asegura que el resto del código no se ejecute después de la redirección
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body{
                background-image: url("https://images.unsplash.com/photo-1491598601902-712af90cc6ee?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
                background-size: cover;
                background-position: center;
                background-attachment: fixed;
                background-repeat: no-repeat;
            }
            .logoutButton{
                border-radius: 15px;
                border: 2px solid #990000;
                padding: 5px;
                margin-left: auto;
                background-color: red;
                color: white;
            }
            .datos{

                position: fixed;
                bottom: 0;
                width: 100%;
                height: 100px;

            }

            .navbar{
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .navbar-collapse{
                display: flex;
                justify-content: space-between;
                width: 100%;
            }
            .logoutButton:hover{
                background-color: #cc0000  !important; /* Rojo más brillante */
                border: 2px solid #ff3333 !important; /* Borde más brillante */
                color: white !important; /* Mantener el texto blanco */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2) !important; /* Sombra para efecto de elevación */
            }
            footer.bg-primary{
                background-color: gray !important;

            }
            .email-icon{
                position: absolute;
                margin-left: -5px;
                margin-top: 2px;

                height: 20px;
                width: 20px;
            }
            .email-text{
                position: relative;
                padding-left: 1px;
            }
            .social-icon{
                margin-left: 10px;
            }
            a img {
                outline: none;
                border: none;
            }
            a:focus{
                outline: none;
            }
            a{
                text-decoration: none;
            }
            .contacto{
                margin-top: 20px;
            }
            .centro{
                height: 100vh;
                align-content: center;
                justify-items: center;
            }
            header{
                margin: 0;
                padding: 0;
                height: 1vh;
            }
        </style>

    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <!-- Botón Toggler -->
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <!-- Contenedor del Menú -->
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                        <ul class="navbar-nav me-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="index.jsp">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href='ServletTecnicas?instruccion=listar'>Técnicas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href='ServletPintores'>Pintores</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href='ServletPinturas?instruccion=listar'>Pinturas</a>
                            </li>
                        </ul>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmModal">
                            Cerrar Sesión
                        </button>
                    </div>
                </div>
            </nav>

        </header>
        

        <section class="centro">
            <div class="container mb-5">
            <div class="row">  
                <div class="col">
                    <h1 style="color: white; font-size: 65px;">BASE DE DATOS DE PINTURAS</h1>
                </div>
            </div>
        </div>
            
        </section>
        
        
        
        <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel">Confirmación</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿Estás seguro de que deseas cerrar sesión?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <form action="ServletUsuarios" method="POST" style="display:inline;">
                            <input type="hidden" name="instruccion" value="cerrarsesion">
                            <button type="submit" class="btn btn-secondary">Confirmar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <footer class="bg-primary text-center text-lg-start text-white">
            <!-- Grid container -->
            <div class="container p-1">
                <!--Grid row-->
                <div class="row my-4">
                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-1 mb-md-0">

                        <div class="rounded-circle bg-white shadow-1-strong d-flex align-items-center justify-content-center mb-4 mx-auto" style="width: 150px; height: 150px;">
                            <img class="logo "src="https://upload.wikimedia.org/wikipedia/commons/d/d3/Logo_de_la_UAZ.svg" alt="Logo de la Uaz Campus Jalpa" width="100px" height="100px" loading="lazy"/>

                        </div>

                        <p class="text-center">Universidad Autonoma De Zacatecas "Campus Jalpa"</p>

                        <ul class="list-unstyled d-flex flex-row justify-content-center">
                            <li>
                                <a class="text-white px-2" href="#!">
                                    <i class="fab fa-facebook-square"></i>
                                </a>
                            </li>
                            <li>
                                <a class="text-white px-2" href="#!">
                                    <i class="fab fa-instagram"></i>
                                </a>
                            </li>
                            <li>
                                <a class="text-white ps-2" href="#!">
                                    <i class="fab fa-youtube"></i>
                                </a>
                            </li>
                        </ul>

                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase mb-4">Creditos</h5>

                        <ul class="list-unstyled">
                            <li class="mb-2">
                                <p  class="text-white"><i class="fas fa-paw pe-3"></i>Carlos Daniel Sandoval Benítez</a>
                            </li>
                            <li class="mb-2">
                                <p class="text-white"><i class="fas fa-paw pe-3"></i>Alfonso Alejandro Gonzales Medina</a>
                            </li>

                        </ul>
                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase mb-4">Contacto</h5>

                        <ul class="list-unstyled">
                            <li class="mb-2 position-relative">
                                <img src="https://cdn-icons-png.flaticon.com/128/901/901125.png" alt="Imagen de correo electronico" class="email-icon">
                                <p class="email-text text-white"><i class="fas fa-paw pe-3"></i>42202380@uaz.edu.mx</p>
                            </li>



                            <li class="mb-2">
                                <img src="https://cdn-icons-png.flaticon.com/128/4511/4511455.png" alt="Imagen de un telefono" class="email-icon">
                                <p  class="text-white"><i class="fas fa-paw pe-3"></i>467 100 4114</a>
                            </li>


                            <li class="mb-2 position-relative">
                                <img src="https://cdn-icons-png.flaticon.com/128/901/901125.png" alt="Imagen de correo electronico" class="email-icon">
                                <p class="email-text text-white"><i class="fas fa-paw pe-3"></i>42202368@uaz.edu.mx</p>
                            </li>

                            <li class="mb-2">
                                <img src="https://cdn-icons-png.flaticon.com/128/4511/4511455.png" alt="Imagen de un telefono" class="email-icon">
                                <p  class="text-white"><i class="fas fa-paw pe-3"></i>467 105 8478</a>
                            </li>


                        </ul>
                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase mb-4">Siguenos</h5>

                        <ul class="list-unstyled">
                            <li>
                                <a href="https://www.facebook.com/carlosdaniel.sandovalbenites">
                                    <img src="https://cdn-icons-png.flaticon.com/128/733/733547.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>

                                <a href="https://www.instagram.com/carlosowo7/">
                                    <img src="https://cdn-icons-png.flaticon.com/128/1409/1409946.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>

                                <a href="https://x.com/CarlosD99706795">
                                    <img src="https://cdn-icons-png.flaticon.com/128/14417/14417709.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>


                            </li>

                            <li class="contacto">
                                <a href="https://www.facebook.com/alfonso.gonzalezmedina.73">
                                    <img src="https://cdn-icons-png.flaticon.com/128/733/733547.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>

                                <a href="https://www.instagram.com/alfonso._.alejandro/">
                                    <img src="https://cdn-icons-png.flaticon.com/128/1409/1409946.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>

                                <a href="https://x.com/CarlosD99706795">
                                    <img src="https://cdn-icons-png.flaticon.com/128/14417/14417709.png" alt="Logo de facebook" width="30px" height="30px" class="social-icon">
                                </a>


                            </li>

                        </ul>
                    </div>
                    <!--Grid column-->
                </div>
                <!--Grid row-->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
                © 2024 Copyright:
                <a class="text-white" href="https://campusjalpa.uaz.edu.mx/">Campusjalpa.com</a>
            </div>
            <!-- Copyright -->
        </footer>

    </body>
</html>