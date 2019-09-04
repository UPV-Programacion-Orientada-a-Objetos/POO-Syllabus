package com.spolancom;

public abstract class Persona {

    private String nombre;
    private String apellido;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public abstract String obtenerNombreCompleto();

}
