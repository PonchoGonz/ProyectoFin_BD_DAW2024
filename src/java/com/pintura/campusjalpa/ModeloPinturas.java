package com.pintura.campusjalpa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author yo mero
 */
public class ModeloPinturas {
    
    // Creamos la variable para obtener el origen de los datos
    private DataSource origenDatos;
    
    public ModeloPinturas(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    /**
     * Retorna todos las pinturas de la base de datos
     * @return
     * throws java.sql.SQLException
     */
    public ArrayList<Pintura> buscarPinturas() throws SQLException {
        ArrayList<Pintura> misPinturas = new ArrayList<>();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            miConexion = origenDatos.getConnection();
            
            //String query = "SELECT * FROM PINTURA, TECNICA WHERE PINTURA.TEC_ID = TECNICA.TEC_ID";
            
            String query = "SELECT * FROM PINTURA JOIN TECNICA ON PINTURA.TEC_ID = TECNICA.TEC_ID JOIN PINTOR ON PINTURA.PIN_ID = PINTOR.PIN_ID;";

            
            stmt = miConexion.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            //CHECAR EL ID Y LA FECHA (EN GENERAL CHECAR TODO EL WHILE)
            while(rs.next()) {
                Pintura miPintura = new Pintura();
                miPintura.setPint_id(rs.getInt("PINT_ID"));
                miPintura.setPint_nombre(rs.getString("PINT_NOMBRE"));
                miPintura.setPint_fecha(rs.getDate("PINT_FECHA"));
                miPintura.setPint_valor(rs.getDouble("PINT_VALOR"));
                
                    Pintor pin = new Pintor();
                    pin.setIdPintor(rs.getInt("PIN_ID"));
                    pin.setPin_nombre(rs.getString("PIN_NOMBRE"));
                    pin.setPin_ciudad(rs.getString("PIN_CIUDAD"));
                    pin.setPin_fecha_nac(rs.getDate("PIN_FECHA_NAC"));
                    pin.setPin_tel(rs.getString("PIN_TEL"));
                    pin.setPin_email(rs.getString("PIN_EMAIL"));
                
                    Tecnica tec = new Tecnica();
                    tec.setIdTecnica(rs.getInt("TEC_ID"));
                    tec.setDescripcion(rs.getString("TEC_TIPO"));
                
                miPintura.setTecnica(tec);
                miPintura.setPintor(pin);
                
                misPinturas.add(miPintura);
                
            } // Fin del bucle While
        } finally {
            try {
                if(rs != null)
                    rs.close();
            } catch(SQLException ex){} // Se ignora
            
            try {
                if(stmt != null)
                    stmt.close();
            } catch(SQLException ex){} // Se ignora
            
            try {
                if(miConexion != null)
                    miConexion.close();
            } catch(SQLException ex){} // Se ignora
        }
        
        return misPinturas;
    } // Fin del método buscarPinturas
    
    /**
     * Inserta una nueva pintura
     * @param miPintura
     * @throws SQLException 
     */
    public void agregarNuevaPintura(Pintura miPintura) throws SQLException { //---------IGUAL CHECAR LO DEL ID
        Connection miConexion = null;
        PreparedStatement stmt = null;
       
        try{
            miConexion = origenDatos.getConnection();

            String query = "INSERT INTO PINTURA (PINT_NOMBRE, PINT_FECHA, PINT_VALOR, PIN_ID, TEC_ID) VALUES "
                    + " (?,?,?,?,?)";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, miPintura.getPint_nombre());
            stmt.setDate(2, (Date) miPintura.getPint_fecha());
            stmt.setDouble(3, miPintura.getPint_valor());
            stmt.setInt(4, miPintura.getPintor().getIdPintor());
            stmt.setInt(5, miPintura.getTecnica().getIdTecnica());

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }       
    } // Fin del método agregarNuevaPintura
    
    /**
     * buscar una pintura en la bd por id
     * @param pin_id
     * @return
     * @throws SQLException 
     */
    @SuppressWarnings("null")
    Pintura buscarPinturaPorId(int pin_id) throws SQLException {
        Pintura miPintura = new Pintura();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();

            // Escribimos la consulta
            /*String query = "SELECT * FROM PINTURA, TECNICA WHERE PINT_ID=? AND"
                    + " PINTURA.TEC_ID = TECNICA.TEC_ID";*/
            
            String query = "SELECT * FROM PINTURA JOIN TECNICA ON PINTURA.TEC_ID = TECNICA.TEC_ID JOIN PINTOR ON PINTURA.PIN_ID = PINTOR.PIN_ID WHERE PINTURA.PINT_ID = ?";
            
            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, pin_id);

            // Guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            // Obtenemos los datos------------------CHECAR AQUÍ AL SER VALOR AUTOINCREMENTADO
            if (rs.next()){
                miPintura.setPint_id(rs.getInt("PINT_ID"));
                miPintura.setPint_nombre(rs.getString("PINT_NOMBRE"));
                miPintura.setPint_fecha(rs.getDate("PINT_FECHA"));
                miPintura.setPint_valor(rs.getDouble("PINT_VALOR"));
                
                    Pintor pin = new Pintor();
                    pin.setIdPintor(rs.getInt("PINTOR.PIN_ID"));
                    pin.setPin_ciudad(rs.getString("PINTOR.PIN_CIUDAD"));
                    pin.setPin_fecha_nac(rs.getDate("PINTOR.PIN_FECHA_NAC"));
                    pin.setPin_tel(rs.getString("PINTOR.PIN_TEL"));
                    pin.setPin_email(rs.getString("PINTOR.PIN_EMAIL"));

                    Tecnica tec = new Tecnica();
                    tec.setIdTecnica(rs.getInt("TECNICA.TEC_ID"));
                    tec.setDescripcion(rs.getString("TECNICA.TEC_TIPO"));
                
                miPintura.setPintor(pin);
                miPintura.setTecnica(tec);           
            }
        }finally{
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        } 
        
        return miPintura;
    } //Fin del método buscarPinturaPorId
    
    /**
     * actualiza una pintura
     * @param miPintura
     * @throws SQLException 
     */
    public void actualizarPintura(Pintura miPintura) throws SQLException {
        Connection miConexion = null;
        PreparedStatement stmt = null;

        
        try{
            miConexion = origenDatos.getConnection();

            String query = "UPDATE PINTURA SET PINT_NOMBRE=?, PINT_FECHA=?, PINT_VALOR=?,"
                    + " PIN_ID=?, TEC_ID=? WHERE PINT_ID=?";


            stmt = miConexion.prepareStatement(query);

            stmt.setString(1, miPintura.getPint_nombre());
            stmt.setDate(2, (Date) miPintura.getPint_fecha());
            stmt.setDouble(3,miPintura.getPint_valor());
            stmt.setInt(4, miPintura.getPintor().getIdPintor());
            stmt.setInt(5, miPintura.getTecnica().getIdTecnica());
            stmt.setInt(6, miPintura.getPint_id());

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }     
    } // Fin del método actualizarPintura
    
    public void eliminarPintura(int pint_id) throws SQLException{
        Connection miConexion = null;
        PreparedStatement stmt = null;
       
        try{
            miConexion = origenDatos.getConnection();

            String query = "DELETE FROM PINTURA WHERE PINT_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, pint_id);          

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){} // Se ignora
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){} // Se ignora
        }
    } // Fin del método eliminarPintura
}