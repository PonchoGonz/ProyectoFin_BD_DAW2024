package com.pintura.campusjalpa;

import java.util.Date;

/**
 *
 * @author gonza
 */
public class Pintor {
    
    // Declaración de campos
    private int pin_id;
    private String pin_nombre;
    private String pin_ciudad;
    private Date pin_fecha_nac; // VERIFICAR
    private String pin_tel;
    private String pin_email;
    
    // Contructor con parámetros
    /*VERIFICAR SI ES NECESARIO INGRESAR pin_id
      AL SER VALOR AUTOGENERADO*/

    public Pintor(int pin_id, String pin_nombre, String pin_ciudad,
            Date pin_fecha_nac, String pin_tel, String pin_email) {
        this.pin_id = pin_id;
        this.pin_nombre = pin_nombre;
        this.pin_ciudad = pin_ciudad;
        this.pin_fecha_nac = pin_fecha_nac;
        this.pin_tel = pin_tel;
        this.pin_email = pin_email;
    }
    
    // Constructor vacío
    public Pintor() {
    }
    
    //Getter y setter
    
    /*IGUAL VER SI ES NECESARIO LO DEL pint_id*/

    public int getIdPintor() {
        return pin_id;
    }

    public void setIdPintor(int pin_id) {
        this.pin_id = pin_id;
    }

    public String getPin_nombre() {
        return pin_nombre;
    }

    public void setPin_nombre(String pin_nombre) {
        if(pin_nombre.trim().equals("")){
            throw new IllegalArgumentException("Escribe un nombre válido");
        }
        this.pin_nombre = pin_nombre;
    }

    public String getPin_ciudad() {
        return pin_ciudad;
    }

    public void setPin_ciudad(String pin_ciudad) {
        if(pin_ciudad.trim().equals("")){
            throw new IllegalArgumentException("Escribe una ciudad válida");
        }
        this.pin_ciudad = pin_ciudad;
    }

    public Date getPin_fecha_nac() {
        return pin_fecha_nac;
    }

    public void setPin_fecha_nac(Date pin_fecha_nac) {
        if(pin_fecha_nac == null){
            throw new IllegalArgumentException("Escribe una fecha válida en el formato: YY-MM-DD");
        }
        this.pin_fecha_nac = pin_fecha_nac;
    }

    public String getPin_tel() {
        return pin_tel;
    }

    public void setPin_tel(String pin_tel) {
        if(pin_tel.trim().equals("")){
            throw new IllegalArgumentException("Escribe un teléfono válido");
        }
        this.pin_tel = pin_tel;
    }

    public String getPin_email() {
        return pin_email;
    }

    public void setPin_email(String pin_email) {
        if(pin_email.trim().equals("")){
            throw new IllegalArgumentException("Escribe un email válido");
        }
        this.pin_email = pin_email;
    }
    
    // Sobreescribir el metodo toString
    @Override
    public String toString() {
        return "PINTOR{" + "PIN_ID=" + pin_id + ", PIN_NOMBRE=" + pin_nombre + ", PIN_CIUDAD=" + pin_ciudad
                + ", PIN_FECHA_NAC=" + pin_fecha_nac + ", PIN_TEL=" + pin_tel + ", PIN_EMAIL=" + pin_email + '}';
    }
}
