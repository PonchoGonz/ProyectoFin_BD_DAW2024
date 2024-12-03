package com.pintura.campusjalpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;
import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;

/**
 *
 * @author yo mero
 */
@WebServlet(name = "ServletPinturas", urlPatterns = {"/ServletPinturas"})
public class ServletPinturas extends HttpServlet {
    
    private ModeloPinturas modeloPinturas;
    private ModeloTecnicas modeloTecnicas;
    private ModeloPintores modeloPintores;
    
    @Resource(name = "jdbc/PinturasDAW2024")
    private DataSource miPool;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        try {
            modeloPinturas = new ModeloPinturas(miPool);
            modeloTecnicas = new ModeloTecnicas(miPool);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Leer el parámetro de le llega desde el formulario
        String bandera = request.getParameter("instruccion");
        
        // Si no se envpia el parámetro listar las Pinturas
        if(bandera == null) {
            bandera = "listar";
        }
        
        // Redirigir el flujo de ejecución al método adecuado
        switch(bandera) {
            case "listar":
                obtenerPinturas(request, response);
                break;
                
            case "nuevo":
                nuevaPintura(request, response);
                break; 
                
            case "insertarBBDD":
                agregarPintura(request, response);
                break;
                
            case "actualizar":
                editarPintura(request, response);
                break;
                
            case "editarBBDD":
                guardarCambiosPintura(request, response);
                break;
                
            case "eliminar":
                eliminarPintura(request, response);
                break;
                
            default:
                obtenerPinturas(request, response);
        }
    }// Fin del método doGet
    
    /**
     * Obtiene las categorías para mostrarlas en el formulario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void nuevaPintura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // Obtenemos las tecnicas y pintores
        ArrayList<Tecnica> misTecnicas;
        ArrayList<Pintor> misPintores;
                
        try{
           // Obtenemos la lista de Técnicas y pintores
            misTecnicas = modeloTecnicas.getTecnicas();
            misPintores = modeloPintores.getPintores();
            
            // Agregamos la lista de técnicas y pintores al request
            request.setAttribute("TODASLASTECNICAS", misTecnicas);
            request.setAttribute("TODOSLOSPINTORES", misPintores);
        
            // Enviar ese request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("inserta_pintura.jsp");
            
            miDispatcher.forward(request, response);         
            
        }catch (SQLException ex){
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }catch (Exception ex){
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    } // Fin del método nuevaPintura
    
    /**
     * Obtiene la lista de Pinturas de la BBDD
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void obtenerPinturas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la lista de las pinturas desde el modelo
        ArrayList<Pintura> misPinturas;
        
        try {
            misPinturas = modeloPinturas.buscarPinturas();
            
            // Agregamos a la lista de productos al request
            request.setAttribute("TODASLASPINTURAS", misPinturas);
            
            // Enviar ese request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("pinturas.jsp");
            
            miDispatcher.forward(request, response);
        } catch(SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch(Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }// Fin del método obtenerPinturas
    
    /**
     * Agrega una pintura a la BBDD
     * @param request
     * @param response 
     */
    private void agregarPintura(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{         
        try {
            
            // Leer la información del producto que viene del formulario
            String pint_nombre = request.getParameter("PINT_NOMBRE");
            //convertir las fechas a java.sql.Date
            String pint_fecha = request.getParameter("PINT_FECHA");
            //formatea los datos a algo que puede leer SQL: YYYY-MM-DD
            java.sql.Date fechaPar = java.sql.Date.valueOf(pint_fecha);
            double pint_valor = Double.parseDouble(request.getParameter("PINT_VALOR"));
            int pin_id =Integer.parseInt(request.getParameter("PIN_ID"));
            int tec_id = Integer.parseInt(request.getParameter("TEC_ID"));
            
            Pintor miPintor = modeloPintores.buscarPintorPorId(pin_id);
            Tecnica miTecnica = modeloTecnicas.buscarTecnicaPorId(tec_id);
            
            Pintura miPintura= new Pintura();
            
            miPintura.setPint_nombre(pint_nombre);
            miPintura.setPint_fecha(fechaPar);
            miPintura.setPint_valor(pint_valor);
            miPintura.setPintor(miPintor);
            miPintura.setTecnica(miTecnica);
            
            // Enviar el objeto al modelo y después insertar el objeto pintura a la bd
            modeloPinturas.agregarNuevaPintura(miPintura);
            
            
            // Redirigir a la página de lista de pinturas
            response.sendRedirect("ServletPinturas?instruccion=listar");
            
        }catch (IllegalArgumentException ex){
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        }catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        }catch(Exception ex){
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        }       
    }// Fin del método agregarPintura
    
    /**
     * Request la página editar_pintura
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void editarPintura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenemos el id de la pintura seleccionada
        int pint_id = Integer.parseInt(request.getParameter("PINT_ID"));
          
        try {
            // Realizamos la consulta a la base de datos para obtener todos los datos del id
            
            Pintura miPintura= modeloPinturas.buscarPinturaPorId(pint_id);          
            
            ArrayList<Tecnica> misTecnicas = new ArrayList<>();
            ArrayList<Pintor> misPintores = new ArrayList<>();
            
            misTecnicas = modeloTecnicas.getTecnicas();
            misPintores = modeloPintores.getPintores();
            
            // Agregamos los datos al request
            request.setAttribute("MIPINTURA", miPintura);
            request.setAttribute("TODASLASTECNICAS", misTecnicas);
            request.setAttribute("TODOSLOSPINTORES", misPintores);
            
            // Enviamos el request a la página JSP
            RequestDispatcher miDispatcher = 
                    request.getRequestDispatcher("editar_pintura.jsp");
            
            miDispatcher.forward(request, response);
            
            
            
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    } // Fin del método editarPintura
    
    /**
     * Guarda los cambios hechos a una pintura en la bd
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void guardarCambiosPintura(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        try{
            // Leer la información del producto que viene del formulario 
            int pint_id = Integer.parseInt(request.getParameter("PINT_ID"));
            String pint_nombre = request.getParameter("PINT_NOMBRE");
            //convertir las fechas a java.sql.Date
            String pint_fecha = request.getParameter("PINT_FECHA");
            //formatea los datos a algo que puede leer SQL: YYY-MM-DD
            java.sql.Date fechaPar = java.sql.Date.valueOf(pint_fecha);
            double pint_valor = Double.parseDouble(request.getParameter("PINT_VALOR"));
            int pin_id =Integer.parseInt(request.getParameter("PIN_ID"));
            int tec_id = Integer.parseInt(request.getParameter("TEC_ID"));
            
            // Buscamos la técnica y el pintor seleccionado en el formulario
            Tecnica miTecnica = modeloTecnicas.buscarTecnicaPorId(tec_id);
            Pintor miPintor = modeloPintores.buscarPintorPorId(pin_id);
            
            // Creamos un objeto de tipo Pintura
            Pintura miPintura = new Pintura();
            
            miPintura.setPint_id(pint_id);
            miPintura.setPint_nombre(pint_nombre);
            miPintura.setPint_fecha(fechaPar);
            miPintura.setPint_valor(pint_valor);
            miPintura.setPintor(miPintor);
            miPintura.setTecnica(miTecnica);;
            
            // Enviar el objeto al modelo y después realizar los cambios en la bd
            modeloPinturas.actualizarPintura(miPintura);
            
            // Mostramos la lista de pinturas
            obtenerPinturas(request, response);
            
        }catch (IllegalArgumentException ex){
            String mensaje = ex.getMessage();
            
            request.setAttribute("mensaje", mensaje);
            
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }catch (SQLException ex) {
            String mensaje = ex.getMessage();
            
            request.setAttribute("mensaje", mensaje);
            
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }catch(Exception ex){
            String mensaje = ex.getMessage();
            
            request.setAttribute("mensaje", mensaje);
            
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        
    }// Fin del método guardarCambiosPintura
    
    /**
     * Elimina el libro seleccionado de la base de datos
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminarPintura(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        int pint_id = Integer.parseInt(request.getParameter("PINT_ID"));
        
        try{
            // Llamamos al método para eliminar el libro
            modeloPinturas.eliminarPintura(pint_id);
            
            // Mostramos la lista de libros
            obtenerPinturas(request, response);
        }catch(SQLException ex){            
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }catch(Exception ex){
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }        
    } // Fin del método eliminarPintura
    
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}// Fin del Servlet