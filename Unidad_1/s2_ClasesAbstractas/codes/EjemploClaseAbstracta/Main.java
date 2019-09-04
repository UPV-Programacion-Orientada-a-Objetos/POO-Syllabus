package com.spolancom;

public class Main {

    public static void main(String[] args) {
        CEmpleado unEmpleado = new CEmpleado();
        unEmpleado.setNombre("Juan");
        unEmpleado.setApellido("Lopez");
        unEmpleado.setClaveEmpleado(1);

        System.out.println(unEmpleado.obtenerNombreCompleto());

        CCliente unCliente = new CCliente();
        unCliente.setNombre("Perdo");
        unCliente.setApellido("Ramirez");
        unCliente.setRfc("RAHP780212");

        System.out.println(unCliente.obtenerNombreCompleto());
    }
}
