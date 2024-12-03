package com.pintura.campusjalpa;

import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yo mero
 */
public class ModeloPintores {
    // Creamos la variable para obtener el origen de datos
    private DataSource origenDatos;
    
    // Constructor con un parámetro
    public ModeloPintores(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    /**
     * retorna la lista de todas los pintores
     * @return
     * @throws SQLException 
     */
    public ArrayList<Pintor> getPintores() throws SQLException {
        // Creamos la lista a retornar
        ArrayList<Pintor> lstPintores = new ArrayList<>();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Nos conectamos mediante el pool de conexiones
            miConexion = origenDatos.getConnection();

            // Creamos la consulta
            String query = "SELECT * FROM PINTOR";

            // Ejecutamos la consulta
            stmt = miConexion.prepareStatement(query);

            // Guardamos la consulta en el resultset
            rs = stmt.executeQuery();
            
            // Recorremos el resultado para guardarlo en el arraylist
            while(rs.next()){
                Pintor miPintor = new Pintor();
                miPintor.setIdPintor(rs.getInt("PIN_ID"));
                miPintor.setPin_nombre(rs.getString("PIN_NOMBRE"));
                miPintor.setPin_ciudad(rs.getString("PIN_CIUDAD"));
                miPintor.setPin_fecha_nac(rs.getDate("PIN_FECHA_NAC"));
                miPintor.setPin_tel(rs.getString("PIN_TEL"));
                miPintor.setPin_email(rs.getString("PIN_EMAIL"));

                //adicionamos el objeto al arraylist
                lstPintores.add(miPintor);       
            }
        } finally {
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }
        
        return lstPintores;
    } // Fin del método
    
    /**
     * Retorna un objeto de tipo Técnica según el id proporcionado
     * @param tec_id
     * @return
     * @throws SQLException 
     */
    public Pintor buscarPintorPorId(int pin_id) throws SQLException {
        Pintor miPintor = new Pintor();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            miConexion = origenDatos.getConnection();

            //escribimos la consulta
            String query = "SELECT * FROM PINTOR WHERE PIN_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, pin_id);

            //guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            //obtenemos los datos
            if (rs.next()){
                miPintor.setIdPintor(rs.getInt("PIN_ID"));
                miPintor.setPin_nombre(rs.getString("PIN_NOMBRE"));
                miPintor.setPin_ciudad(rs.getString("PIN_CIUDAD"));
                miPintor.setPin_fecha_nac(rs.getDate("PIN_FECHA_NAC"));
                miPintor.setPin_tel(rs.getString("PIN_TEL"));
                miPintor.setPin_email(rs.getString("PIN_EMAIL"));
            }
        } finally {
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }
        
        return miPintor;
    } //Fin del método
} // Fin de la clase


