package com.spolancom;

public class CCliente extends Persona {

    private String rfc;

    @Override
    public String obtenerNombreCompleto() {
        return this.getNombre() + " " + this.getApellido();
    }

    public String getRfc()
    {
        return this.rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

}