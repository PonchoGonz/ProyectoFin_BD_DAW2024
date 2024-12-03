package com.pintura.campusjalpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author yo mero
 */
public class ModeloUsuarios {
    //Creamos la variable para obtener el origen de los datos
    private DataSource origenDatos;
    
    //constructor con un parámetro
    public ModeloUsuarios(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    public boolean validarUsuario(String usu_nombre, String usu_contrasenia) throws SQLException {
        Usuario miUsuario = null;
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();

            // Escribimos la consulta
            String query = "SELECT * FROM USUARIO WHERE USU_NOMBRE=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, usu_nombre);

            // Guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            // Obtenemos los datos
            if (rs.next()){
                miUsuario = new Usuario();
                miUsuario.setUsu_id(rs.getInt("USU_ID"));
                miUsuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                miUsuario.setUsu_contrasenia(rs.getString("USU_CONTRASENIA"));
            }
        } finally {
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }
        // Si no se encontró un usuario, devolvemos false
        if (miUsuario == null) {
            return false;
        }
        // Validamos los datos
        if(miUsuario.getUsu_nombre().equals(usu_nombre)){
            if(miUsuario.getUsu_contrasenia().equals(usu_contrasenia)){
                return true;
            }
        }
        return false;
    } // Fin del método
}
