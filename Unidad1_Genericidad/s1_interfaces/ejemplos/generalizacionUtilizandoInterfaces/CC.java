package com.spolancom;

public class CC implements IB, IC {

    public void preguntar(String p) {
        System.out.println(p);
    }


    public void methD(String s) {
        System.out.println("Que hay " + s);
    }

    @Override
    public void methC() {
        System.out.println("Hola perro");

    }

    @Override
    public void methD() {
        System.out.println("Adios perro");
    }

    @Override
    public void methE() {

    }

    @Override
    public void methF() {

    }
}
