package com.pintura.campusjalpa;

import java.util.Date;

/**
 *
 * @author yo mero
 */
public class Pintura {
    
    // Declaración de campos
    private int pint_id;
    private String pint_nombre;
    private Date  pint_fecha; // VERIFICAR
    private double pint_valor;
    private Pintor pintor;
    private Tecnica tecnica;
    
    // Contructor con parámetros
    /*VERIFICAR SI ES NECESARIO INGRESAR pin_id
      AL SER VALOR AUTOGENERADO*/
    public Pintura(int pint_id, String pint_nombre, Date pint_fecha,
            double pint_valor, Pintor pintor, Tecnica tecnica) {
        this.pint_id = pint_id;
        this.pint_nombre = pint_nombre;
        this.pint_fecha = pint_fecha;
        this.pint_valor = pint_valor;
        this.pintor = pintor;
        this.tecnica = tecnica;
    }
    
    // Constructor vacío
    public Pintura() {
    }
    
    //Getter y setter
    
    /*IGUAL VER SI ES NECESARIO LO DEL pin_id*/
    public int getPint_id() {
        return pint_id;
    }

    public void setPint_id(int pint_id) {
        this.pint_id = pint_id;
    }

    public String getPint_nombre() {
        return pint_nombre;
    }

    public void setPint_nombre(String pint_nombre) {
        if(pint_nombre.trim().equals("")){
            throw new IllegalArgumentException("Escribe un nombre válido");
        }
        this.pint_nombre = pint_nombre;
    }

    public Date getPint_fecha() {
        return pint_fecha;
    }

    public void setPint_fecha(Date pint_fecha) {
        if(pint_fecha == null){
            throw new IllegalArgumentException("Escribe una fecha válida en el formato: YY-MM-DD");
        }
        this.pint_fecha = pint_fecha;
    }

    public double getPint_valor() {
        return pint_valor;
    }

    public void setPint_valor(double pint_valor) {
        if(pint_valor < 0){
            throw new IllegalArgumentException("Escribe un valor correcto para la pintura");
        }
        this.pint_valor = pint_valor;
    }

    public Pintor getPintor() {
        return pintor;
    }

    public void setPintor(Pintor pintor) {
        this.pintor = pintor;
    }

    public Tecnica getTecnica() {
        return tecnica;
    }

    public void setTecnica(Tecnica tecnica) {
        this.tecnica = tecnica;
    }
    
    // Sobreescribir el metodo toString
    @Override
    public String toString() {
        return "PINTOR{" + "PINT_ID=" + pint_id + ", PINT_NOMBRE=" + pint_nombre + ", PINT_FECHA=" + pint_fecha
                + ", PINT_VALOR=" + pint_valor + ", PIN_ID=" + pintor + ", TEC_ID=" + tecnica + '}';
    }
}
