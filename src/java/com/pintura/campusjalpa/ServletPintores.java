package com.pintura.campusjalpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;



/**
 *
 * @author yo mero
 */
@WebServlet(name = "ServletPintores", urlPatterns = {"/ServletPintores"})
public class ServletPintores extends HttpServlet {
    
    private ModeloPintores modeloPintores;
    
    @Resource(name = "jdbc/PinturasDAW2024")
    private DataSource miPool;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        try {
            modeloPintores = new ModeloPintores(miPool);
        } catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Leer el parámetro de le llega desde el formulario
        String bandera = request.getParameter("instruccion");
        
        // Si no se envpia el parámetro listar los pintores
        if(bandera == null) {
            bandera = "listar";
        }
        
        // Redirigir el flujo de ejecución al método adecuado
        switch(bandera) {
            case "listar":
                obtenerPintores(request, response);
            break;
            default:
                obtenerPintores(request, response);
        }
    } // Fin del método doGet
    
    /**
     * Obtiene la lista de pintores de la BBDD
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void obtenerPintores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la lista de los pintores desde el modelo
        ArrayList<Pintor> misPintores;
        
        try {
            misPintores = modeloPintores.getPintores();
            
            // Agregamos a la lista de productos al request
            request.setAttribute("TODOSLOSPINTORES", misPintores);
            
            // Enviar ese request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("pintores.jsp");
            
            miDispatcher.forward(request, response);
        } catch(SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch(Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }// Fin del método obtenerPintores
    
    /**
     * Manda un mensaje de error
     * @param ex
     * @param errorjsp
     * @param request
     * @param response
     * @throwws ServletException
     * @throws IOException
     */
    private void mensajeDeError(String ex, String errorjsp, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje", ex);
        
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }// Fin del método mensajeDeError
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}