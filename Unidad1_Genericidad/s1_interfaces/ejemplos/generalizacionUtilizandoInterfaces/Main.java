package com.spolancom;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CC objC = new CC();
        CB objB = new CB();
        CA objA = new CA();

        myMeth(objB);

    }

    public static void myMeth(IB obj)
    {
        obj.methC();
        obj.methD("gato");
    }
}
