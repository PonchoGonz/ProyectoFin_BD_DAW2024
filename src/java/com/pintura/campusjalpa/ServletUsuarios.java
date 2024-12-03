package com.pintura.campusjalpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author yo mero
 */

@WebServlet(name = "ServletUsuarios", urlPatterns = {"/ServletUsuarios"})
public class ServletUsuarios extends HttpServlet {
    
    private ModeloUsuarios modeloUsuarios;
    
    @Resource(name = "jdbc/PinturasDAW2024")
    private DataSource miPool;
    
    @Override
    public void init() throws ServletException {
        super.init();

        try {

            modeloUsuarios = new ModeloUsuarios(miPool);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        // Leer el parametro que le llega desde el formulario
        String bandera = request.getParameter("instruccion");
        
        // Si no se envia el parametro verificar usuario
        if (bandera == null) {
            bandera = "verificarusuario";
        }
        
        // Redirigir a el metodo adecuado
        switch (bandera) {
            case "verificarusuario":
                verificarUsuario(request, response);
            break;
            case "cerrarsesion":
                cerrarSesion(request, response);
            break;
            default:
                verificarUsuario(request, response);
        }
    }
    
    private void verificarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recolectamos lo que escribio el usuario en el formulario de username and password
        String usu_nombre= request.getParameter("USU_NOMBRE");
        String usu_contrasenia = request.getParameter("USU_CONTRASENIA");
        
        try {
            //realizamos la consulta a la base de datos para obtener la respuesta de si existe o no 
            //un usuario con esa contraseña
            boolean miUsuario = modeloUsuarios.validarUsuario(usu_nombre, usu_contrasenia);

            if (miUsuario) {
                HttpSession session = request.getSession();
                session.setAttribute("session", usu_nombre);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("ERRORMENSAJE", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    } // Fin del metodo verificar usuario y crear sesión
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidar la sesión actual
        HttpSession sesion = request.getSession(false); // Evita crear una nueva sesión si no existe
        if (sesion != null) {
            sesion.invalidate();
        }
        // Redirigir a la página de inicio de sesión
        response.sendRedirect("login.jsp");
    } // Fin del metodo que destruye la sesión
    
    /**
     * manda un mensaje de error mostrandolo en la pagina error.jsp
     *
     * @param ex
     * @param errorjsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void mensajeDeError(String ex, String errorjsp, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje", ex);

        request.getRequestDispatcher("error.jsp").forward(request, response);
    } // Fin del metodo mensaje error
}
