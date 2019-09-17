package com.spolancom;

public class CB implements IB {

    public void despedir() {
        System.out.println("Adios");
    }

    @Override
    public void methC() {
        System.out.println("Hola pato");
    }

    @Override
    public void methD() {
        System.out.println("Adios pato");
    }

    @Override
    public void methD(String s) {
        System.out.println("Hola " + s);
    }
}
