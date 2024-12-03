package com.pintura.campusjalpa;

/**
 *
 * @author yo mero
 */
public class Usuario {
    // Declaración de campos
    private int usu_id;
    private String usu_nombre;
    private String usu_contrasenia;

    public Usuario(int usu_id, String usu_nombre, String usu_contraseña) {
        this.usu_id = usu_id;
        this.usu_nombre = usu_nombre;
        this.usu_contrasenia = usu_contraseña;
    }

    public Usuario() {
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_contrasenia() {
        return usu_contrasenia;
    }

    public void setUsu_contrasenia(String usu_contraseña) {
        this.usu_contrasenia = usu_contraseña;
    }
    
    // Sobreescribir el metodo toString
    @Override
    public String toString() {
        return "USUARIO{" + "USU_ID=" + usu_id + ", USU_NOMBRE=" + usu_nombre + ", USU_CONTRASENIA=" + usu_contrasenia+ '}';
    }
}
