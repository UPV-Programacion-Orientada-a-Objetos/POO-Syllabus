package com.spolancom;

public class CEmpleado extends Persona {

    private int claveEmpleado;

    @Override
    public String obtenerNombreCompleto() {
        return this.getApellido() + " " + this.getNombre();
    }

    public int getClaveEmpleado()
    {
        return this.claveEmpleado;
    }

    public void setClaveEmpleado(int claveEmpleado)
    {
        this.claveEmpleado = claveEmpleado;
    }
}
