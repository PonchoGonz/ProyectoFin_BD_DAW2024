package com.pintura.campusjalpa;
/**
 * Representa a la entidad técnicas
 * @author yo mero
 */
public class Tecnica {
    
    // Declaración de los campos
    private int tec_id;
    private String tec_tipo;
    
    // Constructor con dos parámetros
    public Tecnica(int tec_id, String tec_tipo) {
        this.tec_id = tec_id;
        this.tec_tipo = tec_tipo;
    }
    
    // Constructor vacío
    public Tecnica() {
    }
    
    // Descriptores de acceso getter y setter

    public int getIdTecnica() {
        return tec_id;
    }

    public void setIdTecnica(int tec_id) {
        this.tec_id = tec_id;
    }

    public String getDescripcion() {
        return tec_tipo;
    }

    public void setDescripcion(String tec_tipo) {
        if(tec_tipo.trim().equals("")){
            throw new IllegalArgumentException("Escribe una descripción válida para la Técnica");
        }
        this.tec_tipo = tec_tipo;
    }
    
    @Override
    public String toString() {
        return this.getDescripcion();
    }
}
