package com.pintura.campusjalpa;

import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gonza
 */
public class ModeloTecnicas {
    // Creamos la variable para obtener el origen de datos
    private DataSource origenDatos;
    
    // Constructor con un parámetro
    public ModeloTecnicas(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    /**
     * retorna la lista de todas las técnicas
     * @return
     * @throws SQLException 
     */
    public ArrayList<Tecnica> getTecnicas() throws SQLException {
        // Creamos la lista a retornar
        ArrayList<Tecnica> lstTecnicas = new ArrayList<>();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Nos conectamos mediante el pool de conexiones
            miConexion = origenDatos.getConnection();

            // Creamos la consulta
            String query = "SELECT * FROM TECNICA";

            // Ejecutamos la consulta
            stmt = miConexion.prepareStatement(query);

            // Guardamos la consulta en el resultset
            rs = stmt.executeQuery();
            
            // Recorremos el resultado para guardarlo en el arraylist
            while(rs.next()){
                Tecnica miTecnica = new Tecnica();
                miTecnica.setIdTecnica(rs.getInt("TEC_ID"));
                miTecnica.setDescripcion(rs.getString("TEC_TIPO"));

                //adicionamos el objeto al arraylist
                lstTecnicas.add(miTecnica);       
            }
        } finally {
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }
        
        return lstTecnicas;
    } // Fin del método
    
    /**
     * Retorna un objeto de tipo Técnica según el id proporcionado
     * @param tec_id
     * @return
     * @throws SQLException 
     */
    public Tecnica buscarTecnicaPorId(int tec_id) throws SQLException {
        Tecnica miTecnica = new Tecnica();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            miConexion = origenDatos.getConnection();

            //escribimos la consulta
            String query = "SELECT * FROM TECNICA WHERE TEC_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, tec_id);

            //guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            //obtenemos los datos
            if (rs.next()){
                miTecnica.setIdTecnica(rs.getInt("TEC_ID"));
                miTecnica.setDescripcion(rs.getString("TEC_TIPO"));
            }
        } finally {
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }
        
        return miTecnica;
    } //Fin del método
} // Fin de la clase
